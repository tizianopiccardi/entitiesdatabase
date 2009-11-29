/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.node;

import entitiesdb.language.analysis.*;

@SuppressWarnings("nls")
public final class AAttributeInsertbody extends PInsertbody
{
    private TLbracket _lbracket_;
    private PAttributes _attributes_;
    private TRbracket _rbracket_;

    public AAttributeInsertbody()
    {
        // Constructor
    }

    public AAttributeInsertbody(
        @SuppressWarnings("hiding") TLbracket _lbracket_,
        @SuppressWarnings("hiding") PAttributes _attributes_,
        @SuppressWarnings("hiding") TRbracket _rbracket_)
    {
        // Constructor
        setLbracket(_lbracket_);

        setAttributes(_attributes_);

        setRbracket(_rbracket_);

    }

    @Override
    public Object clone()
    {
        return new AAttributeInsertbody(
            cloneNode(this._lbracket_),
            cloneNode(this._attributes_),
            cloneNode(this._rbracket_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAttributeInsertbody(this);
    }

    public TLbracket getLbracket()
    {
        return this._lbracket_;
    }

    public void setLbracket(TLbracket node)
    {
        if(this._lbracket_ != null)
        {
            this._lbracket_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lbracket_ = node;
    }

    public PAttributes getAttributes()
    {
        return this._attributes_;
    }

    public void setAttributes(PAttributes node)
    {
        if(this._attributes_ != null)
        {
            this._attributes_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._attributes_ = node;
    }

    public TRbracket getRbracket()
    {
        return this._rbracket_;
    }

    public void setRbracket(TRbracket node)
    {
        if(this._rbracket_ != null)
        {
            this._rbracket_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rbracket_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._lbracket_)
            + toString(this._attributes_)
            + toString(this._rbracket_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._lbracket_ == child)
        {
            this._lbracket_ = null;
            return;
        }

        if(this._attributes_ == child)
        {
            this._attributes_ = null;
            return;
        }

        if(this._rbracket_ == child)
        {
            this._rbracket_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._lbracket_ == oldChild)
        {
            setLbracket((TLbracket) newChild);
            return;
        }

        if(this._attributes_ == oldChild)
        {
            setAttributes((PAttributes) newChild);
            return;
        }

        if(this._rbracket_ == oldChild)
        {
            setRbracket((TRbracket) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
