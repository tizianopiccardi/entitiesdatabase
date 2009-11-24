/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.node;

import entitiesdb.language.analysis.*;

@SuppressWarnings("nls")
public final class ASingleAttributes extends PAttributes
{
    private TIdentifier _identifier_;
    private TColon _colon_;
    private PValuetype _valuetype_;

    public ASingleAttributes()
    {
        // Constructor
    }

    public ASingleAttributes(
        @SuppressWarnings("hiding") TIdentifier _identifier_,
        @SuppressWarnings("hiding") TColon _colon_,
        @SuppressWarnings("hiding") PValuetype _valuetype_)
    {
        // Constructor
        setIdentifier(_identifier_);

        setColon(_colon_);

        setValuetype(_valuetype_);

    }

    @Override
    public Object clone()
    {
        return new ASingleAttributes(
            cloneNode(this._identifier_),
            cloneNode(this._colon_),
            cloneNode(this._valuetype_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASingleAttributes(this);
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

    public TColon getColon()
    {
        return this._colon_;
    }

    public void setColon(TColon node)
    {
        if(this._colon_ != null)
        {
            this._colon_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._colon_ = node;
    }

    public PValuetype getValuetype()
    {
        return this._valuetype_;
    }

    public void setValuetype(PValuetype node)
    {
        if(this._valuetype_ != null)
        {
            this._valuetype_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._valuetype_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._identifier_)
            + toString(this._colon_)
            + toString(this._valuetype_);
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

        if(this._colon_ == child)
        {
            this._colon_ = null;
            return;
        }

        if(this._valuetype_ == child)
        {
            this._valuetype_ = null;
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

        if(this._colon_ == oldChild)
        {
            setColon((TColon) newChild);
            return;
        }

        if(this._valuetype_ == oldChild)
        {
            setValuetype((PValuetype) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
