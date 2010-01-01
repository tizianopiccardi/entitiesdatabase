/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.analysis;

import java.util.*;
import entitiesdb.language.node.*;

public class ReversedDepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getEOF().apply(this);
        node.getPMain().apply(this);
        outStart(node);
    }

    public void inAQueryMain(AQueryMain node)
    {
        defaultIn(node);
    }

    public void outAQueryMain(AQueryMain node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAQueryMain(AQueryMain node)
    {
        inAQueryMain(node);
        if(node.getQuery() != null)
        {
            node.getQuery().apply(this);
        }
        outAQueryMain(node);
    }

    public void inAInsertMain(AInsertMain node)
    {
        defaultIn(node);
    }

    public void outAInsertMain(AInsertMain node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInsertMain(AInsertMain node)
    {
        inAInsertMain(node);
        if(node.getEntitybody() != null)
        {
            node.getEntitybody().apply(this);
        }
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        if(node.getLess() != null)
        {
            node.getLess().apply(this);
        }
        outAInsertMain(node);
    }

    public void inASimpleQuery(ASimpleQuery node)
    {
        defaultIn(node);
    }

    public void outASimpleQuery(ASimpleQuery node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASimpleQuery(ASimpleQuery node)
    {
        inASimpleQuery(node);
        if(node.getOrderby() != null)
        {
            node.getOrderby().apply(this);
        }
        if(node.getBody() != null)
        {
            node.getBody().apply(this);
        }
        if(node.getDefinedby() != null)
        {
            node.getDefinedby().apply(this);
        }
        if(node.getDistinct() != null)
        {
            node.getDistinct().apply(this);
        }
        if(node.getHead() != null)
        {
            node.getHead().apply(this);
        }
        outASimpleQuery(node);
    }

    public void inAComplexQuery(AComplexQuery node)
    {
        defaultIn(node);
    }

    public void outAComplexQuery(AComplexQuery node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAComplexQuery(AComplexQuery node)
    {
        inAComplexQuery(node);
        if(node.getOrderby() != null)
        {
            node.getOrderby().apply(this);
        }
        if(node.getConditions() != null)
        {
            node.getConditions().apply(this);
        }
        if(node.getQmark() != null)
        {
            node.getQmark().apply(this);
        }
        if(node.getBody() != null)
        {
            node.getBody().apply(this);
        }
        if(node.getDefinedby() != null)
        {
            node.getDefinedby().apply(this);
        }
        if(node.getDistinct() != null)
        {
            node.getDistinct().apply(this);
        }
        if(node.getHead() != null)
        {
            node.getHead().apply(this);
        }
        outAComplexQuery(node);
    }

    public void inAHead(AHead node)
    {
        defaultIn(node);
    }

    public void outAHead(AHead node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAHead(AHead node)
    {
        inAHead(node);
        if(node.getEntitybody() != null)
        {
            node.getEntitybody().apply(this);
        }
        if(node.getEntitytype() != null)
        {
            node.getEntitytype().apply(this);
        }
        outAHead(node);
    }

    public void inASingleBody(ASingleBody node)
    {
        defaultIn(node);
    }

    public void outASingleBody(ASingleBody node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleBody(ASingleBody node)
    {
        inASingleBody(node);
        if(node.getEntitypattern() != null)
        {
            node.getEntitypattern().apply(this);
        }
        outASingleBody(node);
    }

    public void inAListBody(AListBody node)
    {
        defaultIn(node);
    }

    public void outAListBody(AListBody node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListBody(AListBody node)
    {
        inAListBody(node);
        if(node.getEntitypattern() != null)
        {
            node.getEntitypattern().apply(this);
        }
        if(node.getComa() != null)
        {
            node.getComa().apply(this);
        }
        if(node.getBody() != null)
        {
            node.getBody().apply(this);
        }
        outAListBody(node);
    }

    public void inAEntitypattern(AEntitypattern node)
    {
        defaultIn(node);
    }

    public void outAEntitypattern(AEntitypattern node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEntitypattern(AEntitypattern node)
    {
        inAEntitypattern(node);
        if(node.getEntitybody() != null)
        {
            node.getEntitybody().apply(this);
        }
        if(node.getEntitytype() != null)
        {
            node.getEntitytype().apply(this);
        }
        outAEntitypattern(node);
    }

    public void inAIdeEntitytype(AIdeEntitytype node)
    {
        defaultIn(node);
    }

    public void outAIdeEntitytype(AIdeEntitytype node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIdeEntitytype(AIdeEntitytype node)
    {
        inAIdeEntitytype(node);
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        outAIdeEntitytype(node);
    }

    public void inAVariableEntitytype(AVariableEntitytype node)
    {
        defaultIn(node);
    }

    public void outAVariableEntitytype(AVariableEntitytype node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVariableEntitytype(AVariableEntitytype node)
    {
        inAVariableEntitytype(node);
        if(node.getVariable() != null)
        {
            node.getVariable().apply(this);
        }
        outAVariableEntitytype(node);
    }

    public void inAEntitybody(AEntitybody node)
    {
        defaultIn(node);
    }

    public void outAEntitybody(AEntitybody node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEntitybody(AEntitybody node)
    {
        inAEntitybody(node);
        if(node.getRbracket() != null)
        {
            node.getRbracket().apply(this);
        }
        if(node.getAttributes() != null)
        {
            node.getAttributes().apply(this);
        }
        if(node.getLbracket() != null)
        {
            node.getLbracket().apply(this);
        }
        outAEntitybody(node);
    }

    public void inASingleAttributes(ASingleAttributes node)
    {
        defaultIn(node);
    }

    public void outASingleAttributes(ASingleAttributes node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleAttributes(ASingleAttributes node)
    {
        inASingleAttributes(node);
        if(node.getAttribute() != null)
        {
            node.getAttribute().apply(this);
        }
        outASingleAttributes(node);
    }

    public void inAListAttributes(AListAttributes node)
    {
        defaultIn(node);
    }

    public void outAListAttributes(AListAttributes node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListAttributes(AListAttributes node)
    {
        inAListAttributes(node);
        if(node.getAttribute() != null)
        {
            node.getAttribute().apply(this);
        }
        if(node.getComa() != null)
        {
            node.getComa().apply(this);
        }
        if(node.getList() != null)
        {
            node.getList().apply(this);
        }
        outAListAttributes(node);
    }

    public void inAAttribute(AAttribute node)
    {
        defaultIn(node);
    }

    public void outAAttribute(AAttribute node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAttribute(AAttribute node)
    {
        inAAttribute(node);
        if(node.getValue() != null)
        {
            node.getValue().apply(this);
        }
        if(node.getColon() != null)
        {
            node.getColon().apply(this);
        }
        if(node.getAttributetype() != null)
        {
            node.getAttributetype().apply(this);
        }
        outAAttribute(node);
    }

    public void inAIdeAttributetype(AIdeAttributetype node)
    {
        defaultIn(node);
    }

    public void outAIdeAttributetype(AIdeAttributetype node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIdeAttributetype(AIdeAttributetype node)
    {
        inAIdeAttributetype(node);
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        outAIdeAttributetype(node);
    }

    public void inAVariableAttributetype(AVariableAttributetype node)
    {
        defaultIn(node);
    }

    public void outAVariableAttributetype(AVariableAttributetype node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVariableAttributetype(AVariableAttributetype node)
    {
        inAVariableAttributetype(node);
        if(node.getVariable() != null)
        {
            node.getVariable().apply(this);
        }
        outAVariableAttributetype(node);
    }

    public void inAStringValue(AStringValue node)
    {
        defaultIn(node);
    }

    public void outAStringValue(AStringValue node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAStringValue(AStringValue node)
    {
        inAStringValue(node);
        if(node.getString() != null)
        {
            node.getString().apply(this);
        }
        outAStringValue(node);
    }

    public void inAEntityValue(AEntityValue node)
    {
        defaultIn(node);
    }

    public void outAEntityValue(AEntityValue node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEntityValue(AEntityValue node)
    {
        inAEntityValue(node);
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        outAEntityValue(node);
    }

    public void inAVariableValue(AVariableValue node)
    {
        defaultIn(node);
    }

    public void outAVariableValue(AVariableValue node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVariableValue(AVariableValue node)
    {
        inAVariableValue(node);
        if(node.getVariable() != null)
        {
            node.getVariable().apply(this);
        }
        outAVariableValue(node);
    }

    public void inABodyValue(ABodyValue node)
    {
        defaultIn(node);
    }

    public void outABodyValue(ABodyValue node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABodyValue(ABodyValue node)
    {
        inABodyValue(node);
        if(node.getEntitypattern() != null)
        {
            node.getEntitypattern().apply(this);
        }
        outABodyValue(node);
    }

    public void inADesOrderby(ADesOrderby node)
    {
        defaultIn(node);
    }

    public void outADesOrderby(ADesOrderby node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADesOrderby(ADesOrderby node)
    {
        inADesOrderby(node);
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getVariable() != null)
        {
            node.getVariable().apply(this);
        }
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        outADesOrderby(node);
    }

    public void inAAscOrderby(AAscOrderby node)
    {
        defaultIn(node);
    }

    public void outAAscOrderby(AAscOrderby node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAscOrderby(AAscOrderby node)
    {
        inAAscOrderby(node);
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getVariable() != null)
        {
            node.getVariable().apply(this);
        }
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        outAAscOrderby(node);
    }

    public void inASingleConditions(ASingleConditions node)
    {
        defaultIn(node);
    }

    public void outASingleConditions(ASingleConditions node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleConditions(ASingleConditions node)
    {
        inASingleConditions(node);
        if(node.getCondition() != null)
        {
            node.getCondition().apply(this);
        }
        outASingleConditions(node);
    }

    public void inAListConditions(AListConditions node)
    {
        defaultIn(node);
    }

    public void outAListConditions(AListConditions node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListConditions(AListConditions node)
    {
        inAListConditions(node);
        if(node.getConditions() != null)
        {
            node.getConditions().apply(this);
        }
        if(node.getComa() != null)
        {
            node.getComa().apply(this);
        }
        if(node.getCondition() != null)
        {
            node.getCondition().apply(this);
        }
        outAListConditions(node);
    }

    public void inAEqualCondition(AEqualCondition node)
    {
        defaultIn(node);
    }

    public void outAEqualCondition(AEqualCondition node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEqualCondition(AEqualCondition node)
    {
        inAEqualCondition(node);
        if(node.getValue() != null)
        {
            node.getValue().apply(this);
        }
        if(node.getEqual() != null)
        {
            node.getEqual().apply(this);
        }
        if(node.getVariable() != null)
        {
            node.getVariable().apply(this);
        }
        outAEqualCondition(node);
    }
}
