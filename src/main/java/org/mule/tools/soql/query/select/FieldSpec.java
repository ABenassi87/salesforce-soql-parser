/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.tools.soql.query.select;

import org.mule.tools.soql.SOQLDataVisitor;
import org.mule.tools.soql.query.SOQLAbstractData;
import org.mule.tools.soql.query.data.Field;

import java.util.List;

/**
 * Created by damianpelaez on 3/6/16.
 */
public class FieldSpec extends SOQLAbstractData implements SelectSpec {
    private Field field;
    private String alias;

    public FieldSpec() {
    }

    public FieldSpec(Field field, String alias) {
        this.field = field;
        this.alias = alias;
    }

    @Override
    public String toSOQLText() {
        StringBuilder sb = new StringBuilder();

        if(field != null) {
            sb.append(field.toSOQLText());
        }

        if(alias != null) {
            sb.append(" ").append(alias);
        }

        return sb.toString();
    }

    public <T> T accept(SOQLDataVisitor<? extends T> soqlDataVisitor) {
        return soqlDataVisitor.visitFieldSpec(this);
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFieldName() {
        if(field == null) {
            return null;
        }
        return field.getFieldName();
    }

    public List<String> getObjectPrefixNames() {
        if(field == null) {
            return null;
        }
        return field.getObjectPrefixNames();
    }

}
