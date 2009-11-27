/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.analysis;

import java.util.*;
import entitiesdb.language.node.*;

public class AnalysisAdapter implements Analysis
{
    private Hashtable<Node,Object> in;
    private Hashtable<Node,Object> out;

    public Object getIn(Node node)
    {
        if(this.in == null)
        {
            return null;
        }

        return this.in.get(node);
    }

    public void setIn(Node node, Object o)
    {
        if(this.in == null)
        {
            this.in = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.in.put(node, o);
        }
        else
        {
            this.in.remove(node);
        }
    }

    public Object getOut(Node node)
    {
        if(this.out == null)
        {
            return null;
        }

        return this.out.get(node);
    }

    public void setOut(Node node, Object o)
    {
        if(this.out == null)
        {
            this.out = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.out.put(node, o);
        }
        else
        {
            this.out.remove(node);
        }
    }

    public void caseStart(Start node)
    {
        defaultCase(node);
    }

    public void caseAQueryMain(AQueryMain node)
    {
        defaultCase(node);
    }

    public void caseASimpleQuery(ASimpleQuery node)
    {
        defaultCase(node);
    }

    public void caseAComplexQuery(AComplexQuery node)
    {
        defaultCase(node);
    }

    public void caseAHead(AHead node)
    {
        defaultCase(node);
    }

    public void caseASingleBody(ASingleBody node)
    {
        defaultCase(node);
    }

    public void caseAListBody(AListBody node)
    {
        defaultCase(node);
    }

    public void caseASingleConditions(ASingleConditions node)
    {
        defaultCase(node);
    }

    public void caseAListConditions(AListConditions node)
    {
        defaultCase(node);
    }

    public void caseAEqualCondition(AEqualCondition node)
    {
        defaultCase(node);
    }

    public void caseADifferentCondition(ADifferentCondition node)
    {
        defaultCase(node);
    }

    public void caseAGraterCondition(AGraterCondition node)
    {
        defaultCase(node);
    }

    public void caseALessCondition(ALessCondition node)
    {
        defaultCase(node);
    }

    public void caseAStringValue(AStringValue node)
    {
        defaultCase(node);
    }

    public void caseANumberValue(ANumberValue node)
    {
        defaultCase(node);
    }

    public void caseAVarValue(AVarValue node)
    {
        defaultCase(node);
    }

    public void caseAEntityValue(AEntityValue node)
    {
        defaultCase(node);
    }

    public void caseAIdEntity(AIdEntity node)
    {
        defaultCase(node);
    }

    public void caseADefEntity(ADefEntity node)
    {
        defaultCase(node);
    }

    public void caseAIdeVartype(AIdeVartype node)
    {
        defaultCase(node);
    }

    public void caseAVariableVartype(AVariableVartype node)
    {
        defaultCase(node);
    }

    public void caseASingleAttributes(ASingleAttributes node)
    {
        defaultCase(node);
    }

    public void caseAListAttributes(AListAttributes node)
    {
        defaultCase(node);
    }

    public void caseAElementAttribute(AElementAttribute node)
    {
        defaultCase(node);
    }

    public void caseTNewLine(TNewLine node)
    {
        defaultCase(node);
    }

    public void caseTBlank(TBlank node)
    {
        defaultCase(node);
    }

    public void caseTComa(TComa node)
    {
        defaultCase(node);
    }

    public void caseTColon(TColon node)
    {
        defaultCase(node);
    }

    public void caseTDefinedby(TDefinedby node)
    {
        defaultCase(node);
    }

    public void caseTLbracket(TLbracket node)
    {
        defaultCase(node);
    }

    public void caseTRbracket(TRbracket node)
    {
        defaultCase(node);
    }

    public void caseTIdentifier(TIdentifier node)
    {
        defaultCase(node);
    }

    public void caseTVariable(TVariable node)
    {
        defaultCase(node);
    }

    public void caseTString(TString node)
    {
        defaultCase(node);
    }

    public void caseTNumber(TNumber node)
    {
        defaultCase(node);
    }

    public void caseTQmark(TQmark node)
    {
        defaultCase(node);
    }

    public void caseTEqual(TEqual node)
    {
        defaultCase(node);
    }

    public void caseTDifferent(TDifferent node)
    {
        defaultCase(node);
    }

    public void caseTGreater(TGreater node)
    {
        defaultCase(node);
    }

    public void caseTLess(TLess node)
    {
        defaultCase(node);
    }

    public void caseEOF(EOF node)
    {
        defaultCase(node);
    }

    public void defaultCase(@SuppressWarnings("unused") Node node)
    {
        // do nothing
    }
}
