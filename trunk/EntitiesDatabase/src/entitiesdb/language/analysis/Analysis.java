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
    void caseAInsertMain(AInsertMain node);
    void caseASimpleQuery(ASimpleQuery node);
    void caseAComplexQuery(AComplexQuery node);
    void caseAHead(AHead node);
    void caseASingleBody(ASingleBody node);
    void caseAListBody(AListBody node);
    void caseASingleConditions(ASingleConditions node);
    void caseAListConditions(AListConditions node);
    void caseAEqualCondition(AEqualCondition node);
    void caseADifferentCondition(ADifferentCondition node);
    void caseAGraterCondition(AGraterCondition node);
    void caseALessCondition(ALessCondition node);
    void caseAStringValue(AStringValue node);
    void caseAVarValue(AVarValue node);
    void caseAEntityValue(AEntityValue node);
    void caseAIdEntity(AIdEntity node);
    void caseADefEntity(ADefEntity node);
    void caseAIdeVartype(AIdeVartype node);
    void caseAVariableVartype(AVariableVartype node);
    void caseASingleAttributes(ASingleAttributes node);
    void caseAListAttributes(AListAttributes node);
    void caseAElementAttribute(AElementAttribute node);
    void caseASimpleInsert(ASimpleInsert node);
    void caseAAttributeInsertbody(AAttributeInsertbody node);
    void caseAEmptyInsertbody(AEmptyInsertbody node);

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
    void caseTQmark(TQmark node);
    void caseTEqual(TEqual node);
    void caseTDifferent(TDifferent node);
    void caseTGreater(TGreater node);
    void caseTLess(TLess node);
    void caseEOF(EOF node);
}
