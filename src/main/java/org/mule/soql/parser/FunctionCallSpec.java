package org.mule.soql.parser;

import org.antlr.runtime.CommonToken;

/**
 * Created by damianpelaez on 2/23/16.
 */
public class FunctionCallSpec extends SOQLCommonTree {

    public FunctionCallSpec(int tokenType) {
        super(new CommonToken(tokenType, "FUNCTION_CALL_SPEC"));
    }

}
