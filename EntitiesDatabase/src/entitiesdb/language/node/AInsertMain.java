/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.node;

import entitiesdb.language.analysis.*;

@SuppressWarnings("nls")
public final class AInsertMain extends PMain
{
    private TLess _less_;
    private TIdentifier _identifier_;
    private PEntitybody _entitybody_;

    public AInsertMain()
    {
        // Constructor
    }

    public AInsertMain(
        @SuppressWarnings("hiding") TLess _less_,
        @SuppressWarnings("hiding") TIdentifier _identifier_,
        @SuppressWarnings("hiding") PEntitybody _entitybody_)
    {
        // Constructor
        setLess(_less_);

        setIdentifier(_identifier_);

        setEntitybody(_entitybody_);

    }

    @Override
    public Object clone()
    {
        return new AInsertMain(
            cloneNode(this._less_),
            cloneNode(this._identifier_),
            cloneNode(this._entitybody_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAInsertMain(this);
    }

    public TLess getLess()
    {
        return this._less_;
    }

    public void setLess(TLess node)
    {
        if(this._less_ != null)
        {
            this._less_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._less_ = node;
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

    public PEntitybody getEntitybody()
    {
        return this._entitybody_;
    }

    public void setEntitybody(PEntitybody node)
    {
        if(this._entitybody_ != null)
        {
            this._entitybody_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._entitybody_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._less_)
            + toString(this._identifier_)
            + toString(this._entitybody_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._less_ == child)
        {
            this._less_ = null;
            return;
        }

        if(this._identifier_ == child)
        {
            this._identifier_ = null;
            return;
        }

        if(this._entitybody_ == child)
        {
            this._entitybody_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._less_ == oldChild)
        {
            setLess((TLess) newChild);
            return;
        }

        if(this._identifier_ == oldChild)
        {
            setIdentifier((TIdentifier) newChild);
            return;
        }

        if(this._entitybody_ == oldChild)
        {
            setEntitybody((PEntitybody) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}