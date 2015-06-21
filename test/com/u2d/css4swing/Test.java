package com.u2d.css4swing;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import com.u2d.cssparser.CSSParser;
import com.u2d.cssparser.CSSLexer;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Feb 8, 2007
 * Time: 5:21:07 PM
 */
public class Test
{
   
   public static void main(String[] args) throws Exception
   {
      ANTLRInputStream input = new ANTLRInputStream(System.in);
      CSSLexer lexer = new CSSLexer(input);
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      CSSParser parser = new CSSParser(tokens);

      Stylesheet sheet = parser.stylesheet();
      System.out.println(sheet.numRules()+" rules.");
   }
   
}
