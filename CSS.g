grammar CSS;

tokens
{
  CHILD='>';
  SIBLING='+';
}

@header
{
  package com.u2d.cssparser;

  import com.u2d.css4swing.*;
  import com.u2d.css4swing.selector.*;
  import com.u2d.css4swing.property.*;
}
@lexer::header { package com.u2d.cssparser; }

@members
{
  Stylesheet sheet = new Stylesheet();
}

stylesheet returns [Stylesheet value]
    : (nsdecl)* (rule)*
    {
      $value = sheet;
    }
    ;

nsdecl
  : '@namespace' ID PkgName
  {
    sheet.addNamespace($ID.text, $PkgName.text);
  }
  ;

rule
  @init { Rule rule; }
  : s=selector { rule = sheet.addRule($s.value); }
        (',' additionalselector=selector {rule.addSelector($additionalselector.value); } )*
    '{'
      (declaration[rule])*
    '}'
  ;


// ------ selector subgrammar ------

selector returns [Selector value]
 :
 first=selectorterm 
 {
   value = $first.value ;
 }
 (
   ( relator next=selectorterm )
   {
     value = new CompositeSelector(value, $relator.value, $next.value);
   }
   |
   ( next=selectorterm )
   {
     value = new CompositeSelector(value, new Descendant(), $next.value);
   }
 )*
 ;
 
relator returns [Relator value]
 : CHILD { $value = new Child(); }
 | SIBLING { $value = new Sibling(); }
 ;

selectorterm returns [SimpleSelector value]
 @init
 {
   $value = new SimpleSelector();
 }
 : csselementname[$value]
 | cssclass[$value]
 | cssident=Cssident
     {
       $value.setIdent($cssident.text.substring(1));
     }
 | (nsRef[$value])? both=ElementAndClass
   {
     String[] parts = $both.text.split("\\.");
     $value.setElementName(parts[0]);
     $value.setCssClassName(parts[1]);
   }
 ;

nsRef[SimpleSelector s]: ID '|' { $s.setNamespace($ID.text); } ;
csselementname[SimpleSelector s] : (nsRef[$s])? ID  { $s.setElementName($ID.text); } ;
cssclass[SimpleSelector s] : '.' ID { $s.setCssClassName($ID.text); } ;


// ----- declarations ------

declaration[Rule rule]
    : sizingdecl[rule] | colordecl[rule] | imagedecl[rule]
    | cursordecl[rule] | linestyledecl[rule]
    | fontsizedecl[rule] | fontweightdecl[rule]
    | fontstyledecl[rule] | fontfamilydecl[rule]
    | textaligndecl[rule]
    | shorthandpropertydecl[rule]
    | borderradiusdecl[rule]
    | opacitydecl[rule]
    | ignoredecl[rule]
    ;

boxdims[Rule rule]
 @init
 {
   SizingDeclaration leftdecl, rightdecl, topdecl, bottomdecl;
   leftdecl = rightdecl = topdecl = bottomdecl = new MarginLeft(); // to make compiler happy..
 }
 : ('margin' {
                    leftdecl = new MarginLeft();
                    rightdecl = new MarginRight();
                    topdecl = new MarginTop();
                    bottomdecl = new MarginBottom();
                   }
    | 'padding' {
                    leftdecl = new PaddingLeft();
                    rightdecl = new PaddingRight();
                    topdecl = new PaddingTop();
                    bottomdecl = new PaddingBottom();
                   }
    | 'border-width' {
                    leftdecl = new BorderLeftWidth();
                    rightdecl = new BorderRightWidth();
                    topdecl = new BorderTopWidth();
                    bottomdecl = new BorderBottomWidth();
                   }
     ) ':' (
        first=measure second=measure third=measure fourth=measure
        {
          topdecl.setValue($first.value);
          rightdecl.setValue($second.value);
          bottomdecl.setValue($third.value);
          leftdecl.setValue($fourth.value);
        }
        | first=measure second=measure third=measure
        {
          topdecl.setValue($first.value);
          leftdecl.setValue($second.value);
          rightdecl.setValue($second.value);
          bottomdecl.setValue($third.value);
        }
        | first=measure second=measure
        {
          topdecl.setValue($first.value);
          bottomdecl.setValue($first.value);
          leftdecl.setValue($second.value);
          rightdecl.setValue($second.value);
        }
        | first=measure
        {
          topdecl.setValue($first.value);
          bottomdecl.setValue($first.value);
          leftdecl.setValue($first.value);
          rightdecl.setValue($first.value);
        }
     )

    {
      $rule.addDeclaration(leftdecl);
      $rule.addDeclaration(rightdecl);
      $rule.addDeclaration(topdecl);
      $rule.addDeclaration(bottomdecl);
    }
   ';'
 ;

shorthandpropertydecl[Rule rule]
  : boxdims[rule]
  | fontdecl[rule]
  | 'border' ':' borderWidth=measure linestyle borderColor=color ';'
   {
     $rule.addDeclaration(new BorderLeftWidth($borderWidth.value));
     $rule.addDeclaration(new BorderLeftStyle($linestyle.value));
     $rule.addDeclaration(new BorderLeftColor($borderColor.value));
     $rule.addDeclaration(new BorderRightWidth($borderWidth.value));
     $rule.addDeclaration(new BorderRightStyle($linestyle.value));
     $rule.addDeclaration(new BorderRightColor($borderColor.value));
     $rule.addDeclaration(new BorderTopWidth($borderWidth.value));
     $rule.addDeclaration(new BorderTopStyle($linestyle.value));
     $rule.addDeclaration(new BorderTopColor($borderColor.value));
     $rule.addDeclaration(new BorderBottomWidth($borderWidth.value));
     $rule.addDeclaration(new BorderBottomStyle($linestyle.value));
     $rule.addDeclaration(new BorderBottomColor($borderColor.value));
   }
  | 'border-left' ':' borderWidth=measure linestyle borderColor=color ';'
   {
     $rule.addDeclaration(new BorderLeftWidth($borderWidth.value));
     $rule.addDeclaration(new BorderLeftStyle($linestyle.value));
     $rule.addDeclaration(new BorderLeftColor($borderColor.value));
   }
  | 'border-right' ':' borderWidth=measure linestyle borderColor=color ';'
   {
     $rule.addDeclaration(new BorderRightWidth($borderWidth.value));
     $rule.addDeclaration(new BorderRightStyle($linestyle.value));
     $rule.addDeclaration(new BorderRightColor($borderColor.value));
   }
  | 'border-top' ':' borderWidth=measure linestyle borderColor=color ';'
   {
     $rule.addDeclaration(new BorderTopWidth($borderWidth.value));
     $rule.addDeclaration(new BorderTopStyle($linestyle.value));
     $rule.addDeclaration(new BorderTopColor($borderColor.value));
   }
  | 'border-bottom' ':' borderWidth=measure linestyle borderColor=color ';'
   {
     $rule.addDeclaration(new BorderBottomWidth($borderWidth.value));
     $rule.addDeclaration(new BorderBottomStyle($linestyle.value));
     $rule.addDeclaration(new BorderBottomColor($borderColor.value));
   }
  | 'border-style' ':' linestyle ';'
   {
     $rule.addDeclaration(new BorderLeftStyle($linestyle.value));
     $rule.addDeclaration(new BorderRightStyle($linestyle.value));
     $rule.addDeclaration(new BorderTopStyle($linestyle.value));
     $rule.addDeclaration(new BorderBottomStyle($linestyle.value));
   }
  | 'border-color' ':' color ';'
  {
    $rule.addDeclaration(new BorderLeftColor($color.value));
    $rule.addDeclaration(new BorderRightColor($color.value));
    $rule.addDeclaration(new BorderTopColor($color.value));
    $rule.addDeclaration(new BorderBottomColor($color.value));
  }
  ;

fontdecl[Rule rule]
 : 'font' ':' ( bold='bold'
      {
        $rule.addDeclaration( new FontWeight(java.awt.Font.BOLD) );
      } )?
      ( italic='italic'
      {
        $rule.addDeclaration( new FontStyle(java.awt.Font.ITALIC) );
      })?
      fontsize=measure family=fontFamilyValues
      {
        $rule.addDeclaration(new FontSize($fontsize.value));
        $rule.addDeclaration(new FontFamily($family.value));
      } ';'
 ;

opacitydecl[Rule rule]
 : 'opacity' ':' FLOAT ';'
 {
   float value = Float.parseFloat($FLOAT.text);
   Declaration opacity = new Opacity(value);
   $rule.addDeclaration(opacity);
 }
 ;

ignoredecl[Rule rule]
 : '-c4s-ignore' ':' BOOL ';'
 {
   boolean value = Boolean.parseBoolean($BOOL.text);
   Declaration ignore = new Ignore(value);
   $rule.addDeclaration(ignore);
 }
 ;

borderradiusdecl[Rule rule]
  : borderRadiusProp ':' borderRadius ';'
  {
    BorderRadius declaration = $borderRadiusProp.value;
    declaration.setValue($borderRadius.value);
    $rule.addDeclaration(declaration);
  }
  ;
  
borderRadiusProp returns [BorderRadius value]
  : 'border-top-right-radius' { $value = new BorderRadiusTopRight(); }
  | 'border-bottom-right-radius' { $value = new BorderRadiusBottomRight(); }
  | 'border-bottom-left-radius' { $value = new BorderRadiusBottomLeft(); }
  | 'border-top-left-radius' { $value = new BorderRadiusTopLeft(); }
  | 'border-radius' { $value = new BorderRadius(); }
  ;
  
borderRadius returns [BorderRadius value]
  : horiz=measure
  {
    $value = new BorderRadius($horiz.value);
  }
  (vert=measure
    {
      $value = new BorderRadius($horiz.value, $vert.value);
    }
  )?
  ;


// -------------------- sizing declarations .. --

sizingdecl[Rule rule]
    :	sizingProp ':' measure ';'
    {
      SizingDeclaration d = $sizingProp.value;
      d.setValue($measure.value);
      $rule.addDeclaration(d);
    }
    ;

sizingProp returns [SizingDeclaration value]
  : 'margin-left' { $value = new MarginLeft(); }
  | 'margin-right' { $value = new MarginRight(); }
  | 'margin-top' { $value = new MarginTop(); }
  | 'margin-bottom' { $value = new MarginBottom(); }
  | 'border-left-width' { $value = new BorderLeftWidth(); }
  | 'border-right-width' { $value = new BorderRightWidth(); }
  | 'border-top-width' { $value = new BorderTopWidth(); }
  | 'border-bottom-width' { $value = new BorderBottomWidth(); }
  | 'padding-left' { $value = new PaddingLeft(); }
  | 'padding-right' { $value = new PaddingRight(); }
  | 'padding-top' { $value = new PaddingTop(); }
  | 'padding-bottom' { $value = new PaddingBottom(); }
  ;

measure returns [Measure value]
 : j_int unit
 {
   $value = new Measure($j_int.value, $unit.text);
 }
 ;

unit : 'pt' | 'px' | 'em' | 'ex' ;

// -------------------- image declaration .. ---
imagedecl[Rule rule]
  @init
  {
    BackgroundImage d = new BackgroundImage();
  }
  : 'background-image' ':' 
    ( imageResourceRef { d.setValue($imageResourceRef.value); } 
      | 'none' { d.setValue(null); } ) 
    (placementMethod { d.setPlacementMethod($placementMethod.text); } )? ';'
  {
    $rule.addDeclaration(d);
  }
  ;

placementMethod : 'centered' | 'tiled' | 'stretched' ;

imageResourceRef returns [java.awt.Image value]
  : resourceRef
  {
    $value = new javax.swing.ImageIcon($resourceRef.value).getImage();
  }
  ;

resourceRef returns [java.net.URL value]
  :  'resource(' path=StringLiteral ')'
  {
    String text = Utils.unquote($path.text);
    $value = getClass().getClassLoader().getResource(text);
  }
  ;

// -------------------- color declarations .. ---


colordecl[Rule rule]
   :  colorProp ':' paint ';'
   {
     ColorDeclaration d = $colorProp.value;
     d.setValue($paint.value);
     $rule.addDeclaration(d);
   }
   ;

colorProp returns [ColorDeclaration value]
  : 'background-color' { $value = new BackgroundColor(); }
  | 'color' { $value = new ForegroundColor(); }
  | 'border-left-color' { $value = new BorderLeftColor(); }
  | 'border-right-color' { $value = new BorderRightColor(); }
  | 'border-top-color' { $value = new BorderTopColor(); }
  | 'border-bottom-color' { $value = new BorderBottomColor(); }
  ;

paint returns [java.awt.Paint value]
  : color { $value = $color.value; }
  | gradient { $value = $gradient.value; }
  ;
  	
color returns [java.awt.Color value]
  : colorName { $value = new java.awt.Color($colorName.value); }
  | hexColor { $value = new java.awt.Color($hexColor.value); }
  | 'transparent' { $value = new java.awt.Color(0, 0, 0, 0); }
  | rgbSpec { $value = $rgbSpec.value; }
  | rgbaSpec { $value = $rgbaSpec.value; }
  ;

rgbSpec returns [java.awt.Color value]
  : 'rgb(' red=j_int ',' green=j_int ',' blue=j_int ')'
  {
    $value = new java.awt.Color($red.value, $green.value, $blue.value);
  }
  ;
  
rgbaSpec returns [java.awt.Color value]
  : 'rgba(' red=j_int ',' green=j_int ',' blue=j_int ',' alpha=FLOAT ')'
  {
    int alphaInt = (int) (Float.parseFloat($alpha.text) * 255);
    $value = new java.awt.Color($red.value, $green.value, $blue.value, alphaInt);
  }
  ;

gradient returns [java.awt.GradientPaint value]
  : 'gradient(' x1=j_int ',' y1=j_int ',' color1=color ',' x2=j_int ',' y2=j_int ',' color2=color ')' 
  { 
    $value = new java.awt.GradientPaint($x1.value, $y1.value, $color1.value,
                                        $x2.value, $y2.value, $color2.value);
  }
  ;

j_int returns [int value]
  : i=INT
  {
    $value = Integer.parseInt($i.text);
  }
  ;

colorName returns [int value]
  : 'maroon' { $value = 0x800000; }
  | 'red'  { $value = 0xff0000; }
  | 'orange'  { $value = 0xffA500; }
  | 'yellow'  { $value = 0xffff00; }
  | 'olive'  { $value = 0x808000; }
  | 'purple'  { $value = 0x800080; }
  | 'fuchsia'  { $value = 0xff00ff; }
  | 'white'  { $value = 0xffffff; }
  | 'lime'  { $value = 0x00ff00; }
  | 'green'  { $value = 0x008000; }
  | 'navy'  { $value = 0x000080; }
  | 'blue'   { $value = 0x0000ff; }
  | 'aqua'  { $value = 0x00ffff; }
  | 'teal'  { $value = 0x008080; }
  | 'black'  { $value = 0x000000; }
  | 'silver'  { $value = 0xc0c0c0; }
  | 'gray' { $value = 0x808080; }
     ;

hexColor returns [int value]
   : HexNum
     {
       String num =  $HexNum.text.substring(1);
       if (num.length() >= 6)
       {
           $value = (int) Long.parseLong(num, 16);
       }
       else if (num.length() >= 3)
       {
           StringBuffer buf = new StringBuffer();
           buf.append(num.charAt(0))
              .append(num.charAt(0))
              .append(num.charAt(1))
              .append(num.charAt(1))
              .append(num.charAt(2))
              .append(num.charAt(2));
           if (num.length() > 3)
           {
             buf.append(num.charAt(3))
                .append(num.charAt(3));
           }
           $value = (int) Long.parseLong(buf.toString(), 16);
       }
     }
   ;

// --------------------

fontsizedecl[Rule rule]
    :	'font-size' ':' value=measure ';'
    {
      Declaration d = new FontSize($value.value);
      $rule.addDeclaration(d);
    }
    ;
fontweightdecl[Rule rule]
   :  'font-weight' ':' fontWeightValue ';'
   {
     Declaration d = new FontWeight($fontWeightValue.value);
     $rule.addDeclaration(d);
   }
   ;
fontWeightValue returns [int value]
  : 'bold' { $value = java.awt.Font.BOLD; }
  | 'normal' { $value = java.awt.Font.PLAIN; }
  ;

fontstyledecl[Rule rule]
   :  'font-style' ':' fontStyleValue ';'
   {
     $rule.addDeclaration( new FontStyle($fontStyleValue.value) );
   }
   ;
fontStyleValue returns [int value]
  : 'italic' { $value = java.awt.Font.ITALIC; }
  | 'normal' { $value = java.awt.Font.PLAIN; }
  ;

fontfamilydecl[Rule rule]
   :  'font-family' ':' fontFamilyValues ';'
   {
     Declaration d = new FontFamily($fontFamilyValues.value);
     $rule.addDeclaration(d);
   }
   ;

fontFamilyValues returns [List value]
  @init
  {
    $value = new ArrayList();
  }
  : ( item=fontFamilyValue ',' { $value.add($item.value); } )* last=fontFamilyValue
      {
        $value.add($last.value);
      }
  ;

fontFamilyValue returns [String value]
  :  ID { $value = $ID.text; }
  | StringLiteral { $value = Utils.unquote($StringLiteral.text); }
  | genericFamilyName { $value = $genericFamilyName.value; }
  ;

genericFamilyName returns [String value]
  : 'serif' { $value = FontFamily.SERIF; }
  | 'sans-serif' { $value = FontFamily.SANS_SERIF; }
  | 'monospace' { $value = FontFamily.MONOSPACE; }
  /*
  | 'cursive' { $value = "?"; }
  | 'fantasy' { $value = "?"; }
  */
  ;

// ----------------------------------

textaligndecl[Rule rule]
  @init
  {
    TextAlign decl = new TextAlign();
  }
  : 'text-align' ':'
     ('left' { decl.setValue(TextAlign.Alignment.LEFT); }
     | 'right' { decl.setValue(TextAlign.Alignment.RIGHT); }
     | 'center' { decl.setValue(TextAlign.Alignment.CENTER); }
     ) ';'
  {
    $rule.addDeclaration(decl);
  }
  ;

// ----------------------------------

cursordecl[Rule rule]
   : 'cursor' ':' cursor ';'
   {
     $rule.addDeclaration(new Cursor($cursor.value));
   }
   ;

cursor returns [int value]
  : 'crosshair' { $value = java.awt.Cursor.CROSSHAIR_CURSOR; }
  | 'default' { $value = java.awt.Cursor.DEFAULT_CURSOR; }
  | 'pointer' { $value = java.awt.Cursor.HAND_CURSOR; }
  | 'move'  { $value = java.awt.Cursor.MOVE_CURSOR; }
  | 'e-resize'  { $value = java.awt.Cursor.E_RESIZE_CURSOR; }
  | 'ne-resize'  { $value = java.awt.Cursor.NE_RESIZE_CURSOR; }
  | 'nw-resize'  { $value = java.awt.Cursor.NW_RESIZE_CURSOR; }
  | 'n-resize'  { $value = java.awt.Cursor.N_RESIZE_CURSOR; }
  | 'se-resize'  { $value = java.awt.Cursor.SE_RESIZE_CURSOR; }
  | 'sw-resize'  { $value = java.awt.Cursor.SW_RESIZE_CURSOR; }
  | 's-resize'  { $value = java.awt.Cursor.S_RESIZE_CURSOR; }
  | 'w-resize'  { $value = java.awt.Cursor.W_RESIZE_CURSOR; }
  | 'text'  { $value = java.awt.Cursor.TEXT_CURSOR; }
  | 'wait'  { $value = java.awt.Cursor.WAIT_CURSOR; }
  /*
  | 'help'  { $value = java.awt.Cursor.? ; }
  | 'progress' { $value = java.awt.Cursor.? ; }
  */
  ;

// ----------------------------------

linestyledecl[Rule rule]
   : prop=borderStyleProp ':' linestyle ';'
   {
     LineStyleDeclaration d = $prop.value;
     d.setValue($linestyle.value);
     $rule.addDeclaration(d);
   }
   ;

borderStyleProp returns [LineStyleDeclaration value]
  : 'border-left-style' { $value = new BorderLeftStyle(); }
  | 'border-right-style' { $value = new BorderRightStyle(); }
  | 'border-top-style'  { $value = new BorderTopStyle(); }
  | 'border-bottom-style' { $value = new BorderBottomStyle(); }
  ;

/* TODO: finish this: */

linestyle returns [LineStyle value]
  : 'dotted' { $value = LineStyle.DOTTED; }
  | 'dashed' { $value = LineStyle.DASHED; }
  | 'solid' { $value = LineStyle.SOLID; }
  /*
  | 'none'
  | 'hidden'
  | 'double'
  | 'groove'
  | 'ridge'
  | 'inset'
  | 'outset'
  */
  ;

// Lexer Rules ..

BOOL : 'true' | 'false' ;

Percentage : FLOAT '%' ;
INT	:	(Digit)+ ;
HexNum : '#' (HexDigit)+ ;
Cssident : '#' ID ;
ElementAndClass : ID '.' ID;
FLOAT	:	(Digit)+ ('.' Digit*)? ;
ID 	: Nmstart ( Nmchar )* ;
PkgName : (ID ( '.' ID)* ) ;

StringLiteral :  '"' ( EscapeSequence | ~('\\'|'"') )* '"' ;

fragment
Nmchar : Nmstart|Digit|'-' ;

fragment
Nmstart : 'a'..'z'|'A'..'Z'|'_' ;

fragment
EscapeSequence
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UnicodeEscape
    |   OctalEscape
    ;

fragment
OctalEscape
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UnicodeEscape
    :   '\\' 'u' HexDigit HexDigit HexDigit HexDigit
    ;

fragment
HexDigit : (Digit|'a'..'f'|'A'..'F') ;

fragment
Digit : ('0'..'9') ;

WS : (' '|'\t'|'\n'|'\r'|'\f')+ { $channel=HIDDEN; } ;
ML_COMMENT : '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;} ;

