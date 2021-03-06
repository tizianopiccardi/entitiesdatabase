/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.node;

import entitiesdb.language.analysis.*;

@SuppressWarnings("nls")
public final class ADesOrderby extends POrderby
{
    private TPipe _pipe_;
    private TVariable _variable_;
    private TMinus _minus_;

    public ADesOrderby()
    {
        // Constructor
    }

    public ADesOrderby(
        @SuppressWarnings("hiding") TPipe _pipe_,
        @SuppressWarnings("hiding") TVariable _variable_,
        @SuppressWarnings("hiding") TMinus _minus_)
    {
        // Constructor
        setPipe(_pipe_);

        setVariable(_variable_);

        setMinus(_minus_);

    }

    @Override
    public Object clone()
    {
        return new ADesOrderby(
            cloneNode(this._pipe_),
            cloneNode(this._variable_),
            cloneNode(this._minus_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADesOrderby(this);
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

    public TMinus getMinus()
    {
        return this._minus_;
    }

    public void setMinus(TMinus node)
    {
        if(this._minus_ != null)
        {
            this._minus_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._minus_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._pipe_)
            + toString(this._variable_)
            + toString(this._minus_);
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

        if(this._minus_ == child)
        {
            this._minus_ = null;
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

        if(this._minus_ == oldChild)
        {
            setMinus((TMinus) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
