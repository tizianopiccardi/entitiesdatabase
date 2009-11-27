/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.node;

import entitiesdb.language.analysis.*;

@SuppressWarnings("nls")
public final class ADefinitionQuery extends PQuery
{
    private PEntity _entity_;
    private TDefinedby _definedby_;
    private PBody _body_;

    public ADefinitionQuery()
    {
        // Constructor
    }

    public ADefinitionQuery(
        @SuppressWarnings("hiding") PEntity _entity_,
        @SuppressWarnings("hiding") TDefinedby _definedby_,
        @SuppressWarnings("hiding") PBody _body_)
    {
        // Constructor
        setEntity(_entity_);

        setDefinedby(_definedby_);

        setBody(_body_);

    }

    @Override
    public Object clone()
    {
        return new ADefinitionQuery(
            cloneNode(this._entity_),
            cloneNode(this._definedby_),
            cloneNode(this._body_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADefinitionQuery(this);
    }

    public PEntity getEntity()
    {
        return this._entity_;
    }

    public void setEntity(PEntity node)
    {
        if(this._entity_ != null)
        {
            this._entity_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._entity_ = node;
    }

    public TDefinedby getDefinedby()
    {
        return this._definedby_;
    }

    public void setDefinedby(TDefinedby node)
    {
        if(this._definedby_ != null)
        {
            this._definedby_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._definedby_ = node;
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

    @Override
    public String toString()
    {
        return ""
            + toString(this._entity_)
            + toString(this._definedby_)
            + toString(this._body_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._entity_ == child)
        {
            this._entity_ = null;
            return;
        }

        if(this._definedby_ == child)
        {
            this._definedby_ = null;
            return;
        }

        if(this._body_ == child)
        {
            this._body_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._entity_ == oldChild)
        {
            setEntity((PEntity) newChild);
            return;
        }

        if(this._definedby_ == oldChild)
        {
            setDefinedby((TDefinedby) newChild);
            return;
        }

        if(this._body_ == oldChild)
        {
            setBody((PBody) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}