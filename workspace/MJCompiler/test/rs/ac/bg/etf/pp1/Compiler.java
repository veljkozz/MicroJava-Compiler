package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;

/*
 * KOMANDE ZA POKRETANJE IZ CMD
 * 
 * 
 java -cp bin;lib\* rs.ac.bg.etf.pp1.Compiler test\program.mj test\program.obj > test\program.out 2> test\program.err
 java -cp bin;lib\* rs.etf.pp1.mj.runtime.Run test\program.obj
 * 
 */

public class Compiler {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws Exception {
		
		Logger log = Logger.getLogger(Compiler.class);
		
		Reader br = null;
		try {
			// Za setovanje out i err 
			//PrintStream out = new PrintStream("test/testout.out");
			//PrintStream err = new PrintStream("test/testout.err");
			//System.setOut(out);
			//System.setErr(err);
			
			// CHANGE INPUT FILE NAME HERE
			//String inFile = "test/program.mj"; 
			String inFile = args[0]; 
			File sourceCode = new File(inFile);
			//log.info("Compiling source file: " + sourceCode.getAbsolutePath());
			
			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			
			MJParser p = new MJParser(lexer);
	        Symbol s = p.parse();  //pocetak parsiranja
	        
	        Program prog = (Program)(s.value); 
			// ispis sintaksnog stabla
			//log.info(prog.toString(""));

			// ispis prepoznatih programskih konstrukcija
			Tab.init();
			SemanticAnalyzer semPass = new SemanticAnalyzer();
			//prog.traverseBottomUp(v); 
			prog.traverseBottomUp(semPass);
			Tab.dump();
			
			if(semPass.errorDetected || p.errorDetected)
				log.info("Error happened during parsing!");
			else {
				
				File objFile = new File("test/program.obj");
				if(objFile.exists()) objFile.delete();
				
				
				//log.info("Global variable count: " + semPass.globalVarCnt);
				
				Code.dataSize = semPass.globalVarCnt;
				CodeGenerator codeGenerator = new CodeGenerator();
				prog.traverseBottomUp(codeGenerator);
				log.info("Main pc: " + codeGenerator.mainPc);
				System.out.println("Globa vars: "+ semPass.globalVarCnt);
				Code.mainPc = codeGenerator.mainPc;
				Code.write(new FileOutputStream(objFile));
				
			}
			
		} 
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
		}

	}
	
	
}
