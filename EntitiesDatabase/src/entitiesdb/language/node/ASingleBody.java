/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.node;

import entitiesdb.language.analysis.*;

@SuppressWarnings("nls")
public final class ASingleBody extends PBody
{
    private PEntitypattern _entitypattern_;

    public ASingleBody()
    {
        // Constructor
    }

    public ASingleBody(
        @SuppressWarnings("hiding") PEntitypattern _entitypattern_)
    {
        // Constructor
        setEntitypattern(_entitypattern_);

    }

    @Override
    public Object clone()
    {
        return new ASingleBody(
            cloneNode(this._entitypattern_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASingleBody(this);
    }

    public PEntitypattern getEntitypattern()
    {
        return this._entitypattern_;
    }

    public void setEntitypattern(PEntitypattern node)
    {
        if(this._entitypattern_ != null)
        {
            this._entitypattern_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._entitypattern_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._entitypattern_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._entitypattern_ == child)
        {
            this._entitypattern_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._entitypattern_ == oldChild)
        {
            setEntitypattern((PEntitypattern) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
