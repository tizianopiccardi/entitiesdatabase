/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.node;

import entitiesdb.language.analysis.*;

@SuppressWarnings("nls")
public final class ASingleAttributes extends PAttributes
{
    private PAttribute _attribute_;

    public ASingleAttributes()
    {
        // Constructor
    }

    public ASingleAttributes(
        @SuppressWarnings("hiding") PAttribute _attribute_)
    {
        // Constructor
        setAttribute(_attribute_);

    }

    @Override
    public Object clone()
    {
        return new ASingleAttributes(
            cloneNode(this._attribute_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASingleAttributes(this);
    }

    public PAttribute getAttribute()
    {
        return this._attribute_;
    }

    public void setAttribute(PAttribute node)
    {
        if(this._attribute_ != null)
        {
            this._attribute_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._attribute_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._attribute_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._attribute_ == child)
        {
            this._attribute_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._attribute_ == oldChild)
        {
            setAttribute((PAttribute) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}