/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.node;

import entitiesdb.language.analysis.*;

@SuppressWarnings("nls")
public final class AListBody extends PBody
{
    private PBody _body_;
    private TComa _coma_;
    private PEntitypattern _entitypattern_;

    public AListBody()
    {
        // Constructor
    }

    public AListBody(
        @SuppressWarnings("hiding") PBody _body_,
        @SuppressWarnings("hiding") TComa _coma_,
        @SuppressWarnings("hiding") PEntitypattern _entitypattern_)
    {
        // Constructor
        setBody(_body_);

        setComa(_coma_);

        setEntitypattern(_entitypattern_);

    }

    @Override
    public Object clone()
    {
        return new AListBody(
            cloneNode(this._body_),
            cloneNode(this._coma_),
            cloneNode(this._entitypattern_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAListBody(this);
    }

    public PBody getBody()
    {
        return this._body_;
    }

    public void setBody(PBody node)
    {
        if(this._body_ != null)
        {
            this._body_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._body_ = node;
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

    public PEntitypattern getEntitypattern()
    {
        return this._entitypattern_;
    }

    public void setEntitypattern(PEntitypattern node)
    {
        if(this._entitypattern_ != null)
        {
            this._entitypattern_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._entitypattern_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._body_)
            + toString(this._coma_)
            + toString(this._entitypattern_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._body_ == child)
        {
            this._body_ = null;
            return;
        }

        if(this._coma_ == child)
        {
            this._coma_ = null;
            return;
        }

        if(this._entitypattern_ == child)
        {
            this._entitypattern_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._body_ == oldChild)
        {
            setBody((PBody) newChild);
            return;
        }

        if(this._coma_ == oldChild)
        {
            setComa((TComa) newChild);
            return;
        }

        if(this._entitypattern_ == oldChild)
        {
            setEntitypattern((PEntitypattern) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}