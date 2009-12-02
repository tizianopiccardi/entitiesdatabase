/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.node;

import entitiesdb.language.analysis.*;

@SuppressWarnings("nls")
public final class AInsertMain extends PMain
{
    private TGreater _greater_;
    private PInsert _insert_;

    public AInsertMain()
    {
        // Constructor
    }

    public AInsertMain(
        @SuppressWarnings("hiding") TGreater _greater_,
        @SuppressWarnings("hiding") PInsert _insert_)
    {
        // Constructor
        setGreater(_greater_);

        setInsert(_insert_);

    }

    @Override
    public Object clone()
    {
        return new AInsertMain(
            cloneNode(this._greater_),
            cloneNode(this._insert_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAInsertMain(this);
    }

    public TGreater getGreater()
    {
        return this._greater_;
    }

    public void setGreater(TGreater node)
    {
        if(this._greater_ != null)
        {
            this._greater_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._greater_ = node;
    }

    public PInsert getInsert()
    {
        return this._insert_;
    }

    public void setInsert(PInsert node)
    {
        if(this._insert_ != null)
        {
            this._insert_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._insert_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._greater_)
            + toString(this._insert_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._greater_ == child)
        {
            this._greater_ = null;
            return;
        }

        if(this._insert_ == child)
        {
            this._insert_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._greater_ == oldChild)
        {
            setGreater((TGreater) newChild);
            return;
        }

        if(this._insert_ == oldChild)
        {
            setInsert((PInsert) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}