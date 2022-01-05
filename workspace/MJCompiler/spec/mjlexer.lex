
package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT, MULTILINE_COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROG, yytext());}
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"read" 	{ return new_symbol(sym.READ, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"-" 		{ return new_symbol(sym.MINUS, yytext()); }
"*"			{ return new_symbol(sym.STAR, yytext()); }
"/"			{ return new_symbol(sym.SLASH, yytext()); }
"%"			{ return new_symbol(sym.PERCENT, yytext()); }
"=" 		{ return new_symbol(sym.EQUAL, yytext()); }
"==" 		{ return new_symbol(sym.EQ, yytext()); }
"!=" 		{ return new_symbol(sym.NEQ, yytext()); }
">" 		{ return new_symbol(sym.GRT, yytext()); }
"<" 		{ return new_symbol(sym.LSS, yytext()); }
">=" 		{ return new_symbol(sym.GRE, yytext()); }
"<=" 		{ return new_symbol(sym.LSSE, yytext()); }
"&&" 		{ return new_symbol(sym.AND, yytext()); }
"||" 		{ return new_symbol(sym.OR, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"." 		{ return new_symbol(sym.DOT, yytext()); }
"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }
"[" 		{ return new_symbol(sym.LBRACKET, yytext()); }
"]"			{ return new_symbol(sym.RBRACKET, yytext()); }
"const" 	{return new_symbol(sym.CONST, yytext()); }
"class"		{return new_symbol(sym.CLASS, yytext()); }
"record"	{return new_symbol(sym.RECORD, yytext()); }
"void" 		{return new_symbol(sym.VOID, yytext()); }
"if"		{return new_symbol(sym.IF, yytext()); }
"else"		{return new_symbol(sym.ELSE, yytext()); }
"do" 		{return new_symbol(sym.DO, yytext()); }
"while" 	{return new_symbol(sym.WHILE, yytext()); }
"break"		{return new_symbol(sym.BREAK, yytext()); }
"continue"	{return new_symbol(sym.CONTINUE, yytext()); }		
"new"		{return new_symbol(sym.NEW, yytext()); }
"extends"	{return new_symbol(sym.EXTENDS, yytext()); }		
"super"		{return new_symbol(sym.SUPER, yytext()); }	
	


"//" {yybegin(COMMENT);}
<COMMENT> . {yybegin(COMMENT);}
<COMMENT> "\r\n" { yybegin(YYINITIAL); }

<YYINITIAL> "/*" { yybegin(MULTILINE_COMMENT);}
<MULTILINE_COMMENT> . { yybegin(MULTILINE_COMMENT);}
<MULTILINE_COMMENT> "\r\n" { yybegin(MULTILINE_COMMENT);}
<MULTILINE_COMMENT> "*/" { yybegin(YYINITIAL); }

"true"		{return new_symbol (sym.BOOL, true); }
"false" 	{return new_symbol (sym.BOOL, false); }
\"[:letter:]\" { return new_symbol(sym.CHAR, yytext().charAt(1)); }

[0-9]+  { return new_symbol(sym.NUMBER, new Integer (yytext())); }
([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{return new_symbol (sym.IDENT, yytext()); }

. { System.err.println("Lexical error ("+yytext()+") u liniji "+(yyline+1)); }










