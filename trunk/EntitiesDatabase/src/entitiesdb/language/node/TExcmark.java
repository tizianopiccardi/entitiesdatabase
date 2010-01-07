/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.node;

import entitiesdb.language.analysis.*;

@SuppressWarnings("nls")
public final class TExcmark extends Token
{
    public TExcmark()
    {
        super.setText("!");
    }

    public TExcmark(int line, int pos)
    {
        super.setText("!");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TExcmark(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTExcmark(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TExcmark text.");
    }
}