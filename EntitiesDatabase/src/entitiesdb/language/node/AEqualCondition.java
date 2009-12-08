/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.node;

import entitiesdb.language.analysis.*;

@SuppressWarnings("nls")
public final class AEqualCondition extends PCondition
{
    private TVariable _variable_;
    private TEqual _equal_;
    private PValue _value_;

    public AEqualCondition()
    {
        // Constructor
    }

    public AEqualCondition(
        @SuppressWarnings("hiding") TVariable _variable_,
        @SuppressWarnings("hiding") TEqual _equal_,
        @SuppressWarnings("hiding") PValue _value_)
    {
        // Constructor
        setVariable(_variable_);

        setEqual(_equal_);

        setValue(_value_);

    }

    @Override
    public Object clone()
    {
        return new AEqualCondition(
            cloneNode(this._variable_),
            cloneNode(this._equal_),
            cloneNode(this._value_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAEqualCondition(this);
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

    public TEqual getEqual()
    {
        return this._equal_;
    }

    public void setEqual(TEqual node)
    {
        if(this._equal_ != null)
        {
            this._equal_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._equal_ = node;
    }

    public PValue getValue()
    {
        return this._value_;
    }

    public void setValue(PValue node)
    {
        if(this._value_ != null)
        {
            this._value_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._value_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._variable_)
            + toString(this._equal_)
            + toString(this._value_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._variable_ == child)
        {
            this._variable_ = null;
            return;
        }

        if(this._equal_ == child)
        {
            this._equal_ = null;
            return;
        }

        if(this._value_ == child)
        {
            this._value_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._variable_ == oldChild)
        {
            setVariable((TVariable) newChild);
            return;
        }

        if(this._equal_ == oldChild)
        {
            setEqual((TEqual) newChild);
            return;
        }

        if(this._value_ == oldChild)
        {
            setValue((PValue) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}