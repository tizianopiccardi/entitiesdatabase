/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.analysis;

import entitiesdb.language.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseStart(Start node);
    void caseAQueryMain(AQueryMain node);
    void caseAApproxMain(AApproxMain node);
    void caseAInsertMain(AInsertMain node);
    void caseASimpleQuery(ASimpleQuery node);
    void caseAComplexQuery(AComplexQuery node);
    void caseAHead(AHead node);
    void caseASingleBody(ASingleBody node);
    void caseAListBody(AListBody node);
    void caseAEntitypattern(AEntitypattern node);
    void caseAIdeEntitytype(AIdeEntitytype node);
    void caseAVariableEntitytype(AVariableEntitytype node);
    void caseAEntitybody(AEntitybody node);
    void caseASingleAttributes(ASingleAttributes node);
    void caseAListAttributes(AListAttributes node);
    void caseAAttribute(AAttribute node);
    void caseAIdeAttributetype(AIdeAttributetype node);
    void caseAVariableAttributetype(AVariableAttributetype node);
    void caseAStringValue(AStringValue node);
    void caseAEntityValue(AEntityValue node);
    void caseAVariableValue(AVariableValue node);
    void caseABodyValue(ABodyValue node);
    void caseADesOrderby(ADesOrderby node);
    void caseAAscOrderby(AAscOrderby node);
    void caseASingleConditions(ASingleConditions node);
    void caseAListConditions(AListConditions node);
    void caseAEqualCondition(AEqualCondition node);

    void caseTNewLine(TNewLine node);
    void caseTBlank(TBlank node);
    void caseTComa(TComa node);
    void caseTColon(TColon node);
    void caseTDefinedby(TDefinedby node);
    void caseTLbracket(TLbracket node);
    void caseTRbracket(TRbracket node);
    void caseTIdentifier(TIdentifier node);
    void caseTVariable(TVariable node);
    void caseTString(TString node);
    void caseTNumber(TNumber node);
    void caseTQmark(TQmark node);
    void caseTPercent(TPercent node);
    void caseTExcmark(TExcmark node);
    void caseTPipe(TPipe node);
    void caseTPlus(TPlus node);
    void caseTMinus(TMinus node);
    void caseTEqual(TEqual node);
    void caseTDifferent(TDifferent node);
    void caseTLess(TLess node);
    void caseEOF(EOF node);
}
