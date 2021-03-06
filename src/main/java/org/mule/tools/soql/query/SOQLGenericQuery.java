/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.tools.soql.query;

import org.mule.tools.soql.query.from.ObjectSpec;
import org.mule.tools.soql.query.select.SelectSpec;
import org.mule.tools.soql.query.clause.*;

import java.util.List;

/**
 * Created by damianpelaez on 3/6/16.
 */
public abstract class SOQLGenericQuery extends SOQLAbstractData {
    protected SelectClause selectClause;
    protected FromClause fromClause;
    protected String usingFilterScope;
    protected WhereClause whereClause;
    protected WithClause withClause;
    protected GroupByClause groupByClause;
    protected HavingClause havingClause;
    private OrderByClause orderByClause;
    protected ForClause forClause;
    protected UpdateClause updateClause;
    protected Integer limit;
    protected Integer offset;

    @Override
    public String toSOQLText() {
        StringBuilder sb = new StringBuilder();

        if(selectClause != null) {
            sb.append(selectClause.toSOQLText());
        }

        if(fromClause != null) {
            sb.append(" ").append(fromClause.toSOQLText());
        }

        if(usingFilterScope != null) {
            sb.append(" ").append("USING SCOPE").append(" ").append(usingFilterScope);
        }

        if(whereClause != null) {
            sb.append(" ").append(whereClause.toSOQLText());
        }

        if(withClause != null) {
            sb.append(" ").append(withClause.toSOQLText());
        }

        if(groupByClause != null) {
            sb.append(" ").append(groupByClause.toSOQLText());
        }

        if(havingClause != null) {
            sb.append(" ").append(havingClause.toSOQLText());
        }

        if(orderByClause != null) {
            sb.append(" ").append(orderByClause.toSOQLText());
        }

        if(limit != null) {
            sb.append(" ").append("LIMIT").append(" ").append(limit);
        }

        if(offset != null) {
            sb.append(" ").append("OFFSET").append(" ").append(offset);
        }

        if(forClause != null) {
            sb.append(" ").append(forClause.toSOQLText());
        }

        if(updateClause != null) {
            sb.append(" ").append(updateClause.toSOQLText());
        }

        return sb.toString();
    }

    public SOQLGenericQuery() {
    }

    public SelectClause getSelectClause() {
        return selectClause;
    }

    public void setSelectClause(SelectClause selectClause) {
        this.selectClause = selectClause;
    }

    public FromClause getFromClause() {
        return fromClause;
    }

    public void setFromClause(FromClause fromClause) {
        this.fromClause = fromClause;
    }

    public String getUsingFilterScope() {
        return usingFilterScope;
    }

    public void setUsingFilterScope(String usingFilterScope) {
        this.usingFilterScope = usingFilterScope;
    }

    public WhereClause getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(WhereClause whereClause) {
        this.whereClause = whereClause;
    }

    public WithClause getWithClause() {
        return withClause;
    }

    public void setWithClause(WithClause withClause) {
        this.withClause = withClause;
    }

    public GroupByClause getGroupByClause() {
        return groupByClause;
    }

    public void setGroupByClause(GroupByClause groupByClause) {
        this.groupByClause = groupByClause;
    }

    public HavingClause getHavingClause() {
        return havingClause;
    }

    public void setHavingClause(HavingClause havingClause) {
        this.havingClause = havingClause;
    }

    public OrderByClause getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(OrderByClause orderByClause) {
        this.orderByClause = orderByClause;
    }

    public ForClause getForClause() {
        return forClause;
    }

    public void setForClause(ForClause forClause) {
        this.forClause = forClause;
    }

    public UpdateClause getUpdateClause() {
        return updateClause;
    }

    public void setUpdateClause(UpdateClause updateClause) {
        this.updateClause = updateClause;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public List<SelectSpec> getSelectSpecs() {
        if(selectClause == null) {
            return null;
        }
        return selectClause.getSelectSpecs();
    }

    public ObjectSpec getMainObjectSpec() {
        if(fromClause == null) {
            return null;
        }
        return fromClause.getMainObjectSpec();
    }

}
