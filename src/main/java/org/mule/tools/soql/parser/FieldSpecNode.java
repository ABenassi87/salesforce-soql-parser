/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.tools.soql.parser;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;
import org.mule.tools.soql.parser.utils.SOQLCommonTreeUtils;
import org.mule.tools.soql.query.data.Field;
import org.mule.tools.soql.query.select.FieldSpec;

/**
 * Created by damianpelaez on 2/23/16.
 */
public class FieldSpecNode extends SOQLCommonTree {

    public FieldSpecNode(int tokenType) {
        super(new CommonToken(tokenType, "FIELD_SPEC"));
    }

    @Override
    public FieldSpec createSOQLData() {
        FieldSpec fieldSpec = new FieldSpec();

        this.processFirstNode(fieldSpec);
        this.processSecondNode(fieldSpec);

        return fieldSpec;
    }

    private void processFirstNode(FieldSpec fieldSpec) {
        CommonTree child = (CommonTree) this.getChild(0);

        if (child == null) { return; }

        this.fillField(child, fieldSpec);
    }

    private void processSecondNode(FieldSpec fieldSpec) {
        CommonTree child = (CommonTree) this.getChild(1);

        if (child == null) { return; }

        fieldSpec.setAlias(child.getText());
    }

    private void fillField(CommonTree node, FieldSpec fieldSpec) {
        if (!SOQLCommonTreeUtils.matchesAnyType(node, SOQLParser.FIELD)) { return; }

        SOQLCommonTree soqlNode = (SOQLCommonTree) node;

        fieldSpec.setField((Field) soqlNode.createSOQLData());
    }

}
