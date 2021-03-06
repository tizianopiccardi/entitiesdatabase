/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.node;

import entitiesdb.language.analysis.*;

@SuppressWarnings("nls")
public final class AListConditions extends PConditions
{
    private PCondition _condition_;
    private TComa _coma_;
    private PConditions _conditions_;

    public AListConditions()
    {
        // Constructor
    }

    public AListConditions(
        @SuppressWarnings("hiding") PCondition _condition_,
        @SuppressWarnings("hiding") TComa _coma_,
        @SuppressWarnings("hiding") PConditions _conditions_)
    {
        // Constructor
        setCondition(_condition_);

        setComa(_coma_);

        setConditions(_conditions_);

    }

    @Override
    public Object clone()
    {
        return new AListConditions(
            cloneNode(this._condition_),
            cloneNode(this._coma_),
            cloneNode(this._conditions_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAListConditions(this);
    }

    public PCondition getCondition()
    {
        return this._condition_;
    }

    public void setCondition(PCondition node)
    {
        if(this._condition_ != null)
        {
            this._condition_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._condition_ = node;
    }

    public TComa getComa()
    {
        return this._coma_;
    }

    public void setComa(TComa node)
    {
        if(this._coma_ != null)
        {
            this._coma_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._coma_ = node;
    }

    public PConditions getConditions()
    {
        return this._conditions_;
    }

    public void setConditions(PConditions node)
    {
        if(this._conditions_ != null)
        {
            this._conditions_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._conditions_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._condition_)
            + toString(this._coma_)
            + toString(this._conditions_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._condition_ == child)
        {
            this._condition_ = null;
            return;
        }

        if(this._coma_ == child)
        {
            this._coma_ = null;
            return;
        }

        if(this._conditions_ == child)
        {
            this._conditions_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._condition_ == oldChild)
        {
            setCondition((PCondition) newChild);
            return;
        }

        if(this._coma_ == oldChild)
        {
            setComa((TComa) newChild);
            return;
        }

        if(this._conditions_ == oldChild)
        {
            setConditions((PConditions) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
