/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.node;

import entitiesdb.language.analysis.*;

@SuppressWarnings("nls")
public final class AQueryMain extends PMain
{
    private PQuery _query_;

    public AQueryMain()
    {
        // Constructor
    }

    public AQueryMain(
        @SuppressWarnings("hiding") PQuery _query_)
    {
        // Constructor
        setQuery(_query_);

    }

    @Override
    public Object clone()
    {
        return new AQueryMain(
            cloneNode(this._query_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAQueryMain(this);
    }

    public PQuery getQuery()
    {
        return this._query_;
    }

    public void setQuery(PQuery node)
    {
        if(this._query_ != null)
        {
            this._query_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._query_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._query_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._query_ == child)
        {
            this._query_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._query_ == oldChild)
        {
            setQuery((PQuery) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
