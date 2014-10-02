package ast;

import visitor.*;
//Repeat tree is created an an new token and identifier 
//invokes the visitrEPEATtREE Method whoch is a subclass of ast
public class RepeatTree extends AST {

    public RepeatTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitRepeatTree(this);
    }
}

