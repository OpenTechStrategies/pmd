/* Generated By:JJTree: Do not edit this line. ASTPrimaryPrefix.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=net.sourceforge.pmd.lang.ast.AbstractNode,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package net.sourceforge.pmd.lang.plsql.ast;

public
class ASTPrimaryPrefix extends SimpleNode {
  public ASTPrimaryPrefix(int id) {
    super(id);
  }

  public ASTPrimaryPrefix(PLSQLParser p, int id) {
    super(p, id);
  }

  private boolean usesSelfModifier;

  public void setUsesSelfModifier() {
  usesSelfModifier = true;
  }

  public boolean usesSelfModifier() {
  return this.usesSelfModifier;
  }



  /** Accept the visitor. **/
  public Object jjtAccept(PLSQLParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=56b51851af73d3dc0fa7f725e2139097 (do not edit this line) */