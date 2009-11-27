/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.node;

import entitiesdb.language.analysis.*;

@SuppressWarnings("nls")
public final class AGraterCondition extends PCondition
{
    private PValue _left_;
    private TGreater _greater_;
    private PValue _right_;

    public AGraterCondition()
    {
        // Constructor
    }

    public AGraterCondition(
        @SuppressWarnings("hiding") PValue _left_,
        @SuppressWarnings("hiding") TGreater _greater_,
        @SuppressWarnings("hiding") PValue _right_)
    {
        // Constructor
        setLeft(_left_);

        setGreater(_greater_);

        setRight(_right_);

    }

    @Override
    public Object clone()
    {
        return new AGraterCondition(
            cloneNode(this._left_),
            cloneNode(this._greater_),
            cloneNode(this._right_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAGraterCondition(this);
    }

    public PValue getLeft()
    {
        return this._left_;
    }

    public void setLeft(PValue node)
    {
        if(this._left_ != null)
        {
            this._left_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._left_ = node;
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

    public PValue getRight()
    {
        return this._right_;
    }

    public void setRight(PValue node)
    {
        if(this._right_ != null)
        {
            this._right_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._right_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._left_)
            + toString(this._greater_)
            + toString(this._right_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._left_ == child)
        {
            this._left_ = null;
            return;
        }

        if(this._greater_ == child)
        {
            this._greater_ = null;
            return;
        }

        if(this._right_ == child)
        {
            this._right_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._left_ == oldChild)
        {
            setLeft((PValue) newChild);
            return;
        }

        if(this._greater_ == oldChild)
        {
            setGreater((TGreater) newChild);
            return;
        }

        if(this._right_ == oldChild)
        {
            setRight((PValue) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
