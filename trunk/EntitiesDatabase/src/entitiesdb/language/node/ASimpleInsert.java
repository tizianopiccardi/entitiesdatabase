/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.node;

import entitiesdb.language.analysis.*;

@SuppressWarnings("nls")
public final class ASimpleInsert extends PInsert
{
    private TIdentifier _identifier_;
    private PInsertbody _insertbody_;

    public ASimpleInsert()
    {
        // Constructor
    }

    public ASimpleInsert(
        @SuppressWarnings("hiding") TIdentifier _identifier_,
        @SuppressWarnings("hiding") PInsertbody _insertbody_)
    {
        // Constructor
        setIdentifier(_identifier_);

        setInsertbody(_insertbody_);

    }

    @Override
    public Object clone()
    {
        return new ASimpleInsert(
            cloneNode(this._identifier_),
            cloneNode(this._insertbody_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASimpleInsert(this);
    }

    public TIdentifier getIdentifier()
    {
        return this._identifier_;
    }

    public void setIdentifier(TIdentifier node)
    {
        if(this._identifier_ != null)
        {
            this._identifier_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._identifier_ = node;
    }

    public PInsertbody getInsertbody()
    {
        return this._insertbody_;
    }

    public void setInsertbody(PInsertbody node)
    {
        if(this._insertbody_ != null)
        {
            this._insertbody_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._insertbody_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._identifier_)
            + toString(this._insertbody_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._identifier_ == child)
        {
            this._identifier_ = null;
            return;
        }

        if(this._insertbody_ == child)
        {
            this._insertbody_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._identifier_ == oldChild)
        {
            setIdentifier((TIdentifier) newChild);
            return;
        }

        if(this._insertbody_ == oldChild)
        {
            setInsertbody((PInsertbody) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
