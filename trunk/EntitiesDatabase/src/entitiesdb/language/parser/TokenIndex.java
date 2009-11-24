/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.parser;

import entitiesdb.language.node.*;
import entitiesdb.language.analysis.*;

class TokenIndex extends AnalysisAdapter
{
    int index;

    @Override
    public void caseTComa(@SuppressWarnings("unused") TComa node)
    {
        this.index = 0;
    }

    @Override
    public void caseTColon(@SuppressWarnings("unused") TColon node)
    {
        this.index = 1;
    }

    @Override
    public void caseTDefined(@SuppressWarnings("unused") TDefined node)
    {
        this.index = 2;
    }

    @Override
    public void caseTLbracket(@SuppressWarnings("unused") TLbracket node)
    {
        this.index = 3;
    }

    @Override
    public void caseTRbracket(@SuppressWarnings("unused") TRbracket node)
    {
        this.index = 4;
    }

    @Override
    public void caseTIdentifier(@SuppressWarnings("unused") TIdentifier node)
    {
        this.index = 5;
    }

    @Override
    public void caseTVariable(@SuppressWarnings("unused") TVariable node)
    {
        this.index = 6;
    }

    @Override
    public void caseTString(@SuppressWarnings("unused") TString node)
    {
        this.index = 7;
    }

    @Override
    public void caseTWith(@SuppressWarnings("unused") TWith node)
    {
        this.index = 8;
    }

    @Override
    public void caseEOF(@SuppressWarnings("unused") EOF node)
    {
        this.index = 9;
    }
}
