/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.node;

import entitiesdb.language.analysis.*;

@SuppressWarnings("nls")
public final class AHead extends PHead
{
    private PEntity _entity_;

    public AHead()
    {
        // Constructor
    }

    public AHead(
        @SuppressWarnings("hiding") PEntity _entity_)
    {
        // Constructor
        setEntity(_entity_);

    }

    @Override
    public Object clone()
    {
        return new AHead(
            cloneNode(this._entity_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAHead(this);
    }

    public PEntity getEntity()
    {
        return this._entity_;
    }

    public void setEntity(PEntity node)
    {
        if(this._entity_ != null)
        {
            this._entity_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._entity_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._entity_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._entity_ == child)
        {
            this._entity_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._entity_ == oldChild)
        {
            setEntity((PEntity) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}