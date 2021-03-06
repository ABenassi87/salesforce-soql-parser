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
import org.mule.tools.soql.query.condition.ConditionField;
import org.mule.tools.soql.query.condition.FieldBasedCondition;
import org.mule.tools.soql.query.condition.operator.ComparisonOperator;
import org.mule.tools.soql.query.data.Literal;

/**
 * Created by damianpelaez on 2/26/16.
 */
public class FieldBasedConditionNode extends SOQLCommonTree {

    public FieldBasedConditionNode(int tokenType) {
        super(new CommonToken(tokenType, "FIELD_BASED_CONDITION"));
    }

    @Override
    public FieldBasedCondition createSOQLData() {
        FieldBasedCondition fieldBasedCondition = new FieldBasedCondition();

        this.processFirstNode(fieldBasedCondition);
        this.processSecondNode(fieldBasedCondition);
        this.processThirdNode(fieldBasedCondition);

        return fieldBasedCondition;
    }

    private void processFirstNode(FieldBasedCondition fieldBasedCondition) {
        CommonTree child = (CommonTree) this.getChild(0);

        this.fillConditionField(child, fieldBasedCondition);
    }

    private void processSecondNode(FieldBasedCondition fieldBasedCondition) {
        CommonTree child = (CommonTree) this.getChild(1);

        this.fillComparisonOperator(child, fieldBasedCondition);
    }

    private void processThirdNode(FieldBasedCondition fieldBasedCondition) {
        CommonTree child = (CommonTree) this.getChild(2);

        this.fillLiteral(child, fieldBasedCondition);
    }

    private void fillConditionField(CommonTree node, FieldBasedCondition fieldBasedCondition) {
        if (!SOQLCommonTreeUtils.matchesAnyType(node, SOQLParser.FIELD, SOQLParser.FUNCTION_CALL)) { return; }

        SOQLCommonTree soqlNode = (SOQLCommonTree) node;

        fieldBasedCondition.setConditionField((ConditionField) soqlNode.createSOQLData());
    }

    private void fillComparisonOperator(CommonTree node, FieldBasedCondition fieldBasedCondition) {
        String operatorName = SOQLCommonTreeUtils.getOperatorName(node);

        fieldBasedCondition.setOperator(ComparisonOperator.get(operatorName));
    }

    private void fillLiteral(CommonTree node, FieldBasedCondition fieldBasedCondition) {
        if (!SOQLCommonTreeUtils.matchesAnyType(node, SOQLParser.LITERAL)) { return; }

        SOQLCommonTree soqlNode = (SOQLCommonTree) node;

        fieldBasedCondition.setLiteral((Literal) soqlNode.createSOQLData());
    }

}
