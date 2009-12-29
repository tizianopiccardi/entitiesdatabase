/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.node;

import entitiesdb.language.analysis.*;

@SuppressWarnings("nls")
public final class AAscOrderby extends POrderby
{
    private TPipe _pipe_;
    private TVariable _variable_;
    private TPlus _plus_;

    public AAscOrderby()
    {
        // Constructor
    }

    public AAscOrderby(
        @SuppressWarnings("hiding") TPipe _pipe_,
        @SuppressWarnings("hiding") TVariable _variable_,
        @SuppressWarnings("hiding") TPlus _plus_)
    {
        // Constructor
        setPipe(_pipe_);

        setVariable(_variable_);

        setPlus(_plus_);

    }

    @Override
    public Object clone()
    {
        return new AAscOrderby(
            cloneNode(this._pipe_),
            cloneNode(this._variable_),
            cloneNode(this._plus_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAscOrderby(this);
    }

    public TPipe getPipe()
    {
        return this._pipe_;
    }

    public void setPipe(TPipe node)
    {
        if(this._pipe_ != null)
        {
            this._pipe_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._pipe_ = node;
    }

    public TVariable getVariable()
    {
        return this._variable_;
    }

    public void setVariable(TVariable node)
    {
        if(this._variable_ != null)
        {
            this._variable_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._variable_ = node;
    }

    public TPlus getPlus()
    {
        return this._plus_;
    }

    public void setPlus(TPlus node)
    {
        if(this._plus_ != null)
        {
            this._plus_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._plus_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._pipe_)
            + toString(this._variable_)
            + toString(this._plus_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._pipe_ == child)
        {
            this._pipe_ = null;
            return;
        }

        if(this._variable_ == child)
        {
            this._variable_ = null;
            return;
        }

        if(this._plus_ == child)
        {
            this._plus_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._pipe_ == oldChild)
        {
            setPipe((TPipe) newChild);
            return;
        }

        if(this._variable_ == oldChild)
        {
            setVariable((TVariable) newChild);
            return;
        }

        if(this._plus_ == oldChild)
        {
            setPlus((TPlus) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
