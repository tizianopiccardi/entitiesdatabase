/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.node;

import entitiesdb.language.analysis.*;

@SuppressWarnings("nls")
public final class AEntityValue extends PValue
{
    private TIdentifier _identifier_;
    private POptdefinition _optdefinition_;

    public AEntityValue()
    {
        // Constructor
    }

    public AEntityValue(
        @SuppressWarnings("hiding") TIdentifier _identifier_,
        @SuppressWarnings("hiding") POptdefinition _optdefinition_)
    {
        // Constructor
        setIdentifier(_identifier_);

        setOptdefinition(_optdefinition_);

    }

    @Override
    public Object clone()
    {
        return new AEntityValue(
            cloneNode(this._identifier_),
            cloneNode(this._optdefinition_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAEntityValue(this);
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

    public POptdefinition getOptdefinition()
    {
        return this._optdefinition_;
    }

    public void setOptdefinition(POptdefinition node)
    {
        if(this._optdefinition_ != null)
        {
            this._optdefinition_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._optdefinition_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._identifier_)
            + toString(this._optdefinition_);
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

        if(this._optdefinition_ == child)
        {
            this._optdefinition_ = null;
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

        if(this._optdefinition_ == oldChild)
        {
            setOptdefinition((POptdefinition) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}