/* This file was generated by SableCC (http://www.sablecc.org/). */

package entitiesdb.language.parser;

import entitiesdb.language.lexer.*;
import entitiesdb.language.node.*;
import entitiesdb.language.analysis.*;
import java.util.*;

import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;

@SuppressWarnings("nls")
public class Parser
{
    public final Analysis ignoredTokens = new AnalysisAdapter();

    protected ArrayList nodeList;

    private final Lexer lexer;
    private final ListIterator stack = new LinkedList().listIterator();
    private int last_pos;
    private int last_line;
    private Token last_token;
    private final TokenIndex converter = new TokenIndex();
    private final int[] action = new int[2];

    private final static int SHIFT = 0;
    private final static int REDUCE = 1;
    private final static int ACCEPT = 2;
    private final static int ERROR = 3;

    public Parser(@SuppressWarnings("hiding") Lexer lexer)
    {
        this.lexer = lexer;
    }

    protected void filter() throws ParserException, LexerException, IOException
    {
        // Empty body
    }

    private void push(int numstate, ArrayList listNode, boolean hidden) throws ParserException, LexerException, IOException
    {
        this.nodeList = listNode;

        if(!hidden)
        {
            filter();
        }

        if(!this.stack.hasNext())
        {
            this.stack.add(new State(numstate, this.nodeList));
            return;
        }

        State s = (State) this.stack.next();
        s.state = numstate;
        s.nodes = this.nodeList;
    }

    private int goTo(int index)
    {
        int state = state();
        int low = 1;
        int high = gotoTable[index].length - 1;
        int value = gotoTable[index][0][1];

        while(low <= high)
        {
            int middle = (low + high) / 2;

            if(state < gotoTable[index][middle][0])
            {
                high = middle - 1;
            }
            else if(state > gotoTable[index][middle][0])
            {
                low = middle + 1;
            }
            else
            {
                value = gotoTable[index][middle][1];
                break;
            }
        }

        return value;
    }

    private int state()
    {
        State s = (State) this.stack.previous();
        this.stack.next();
        return s.state;
    }

    private ArrayList pop()
    {
        return ((State) this.stack.previous()).nodes;
    }

    private int index(Switchable token)
    {
        this.converter.index = -1;
        token.apply(this.converter);
        return this.converter.index;
    }

    @SuppressWarnings("unchecked")
    public Start parse() throws ParserException, LexerException, IOException
    {
        push(0, null, true);
        List<Node> ign = null;
        while(true)
        {
            while(index(this.lexer.peek()) == -1)
            {
                if(ign == null)
                {
                    ign = new LinkedList<Node>();
                }

                ign.add(this.lexer.next());
            }

            if(ign != null)
            {
                this.ignoredTokens.setIn(this.lexer.peek(), ign);
                ign = null;
            }

            this.last_pos = this.lexer.peek().getPos();
            this.last_line = this.lexer.peek().getLine();
            this.last_token = this.lexer.peek();

            int index = index(this.lexer.peek());
            this.action[0] = Parser.actionTable[state()][0][1];
            this.action[1] = Parser.actionTable[state()][0][2];

            int low = 1;
            int high = Parser.actionTable[state()].length - 1;

            while(low <= high)
            {
                int middle = (low + high) / 2;

                if(index < Parser.actionTable[state()][middle][0])
                {
                    high = middle - 1;
                }
                else if(index > Parser.actionTable[state()][middle][0])
                {
                    low = middle + 1;
                }
                else
                {
                    this.action[0] = Parser.actionTable[state()][middle][1];
                    this.action[1] = Parser.actionTable[state()][middle][2];
                    break;
                }
            }

            switch(this.action[0])
            {
                case SHIFT:
		    {
		        ArrayList list = new ArrayList();
		        list.add(this.lexer.next());
                        push(this.action[1], list, false);
                    }
		    break;
                case REDUCE:
                    switch(this.action[1])
                    {
                    case 0: /* reduce AQueryMain */
		    {
			ArrayList list = new0();
			push(goTo(0), list, false);
		    }
		    break;
                    case 1: /* reduce AAinsertmain1Main */
		    {
			ArrayList list = new1();
			push(goTo(0), list, false);
		    }
		    break;
                    case 2: /* reduce AAinsertmain2Main */
		    {
			ArrayList list = new2();
			push(goTo(0), list, false);
		    }
		    break;
                    case 3: /* reduce ASimpleQuery */
		    {
			ArrayList list = new3();
			push(goTo(1), list, false);
		    }
		    break;
                    case 4: /* reduce AComplexQuery */
		    {
			ArrayList list = new4();
			push(goTo(1), list, false);
		    }
		    break;
                    case 5: /* reduce AHead */
		    {
			ArrayList list = new5();
			push(goTo(2), list, false);
		    }
		    break;
                    case 6: /* reduce ASingleBody */
		    {
			ArrayList list = new6();
			push(goTo(3), list, false);
		    }
		    break;
                    case 7: /* reduce AListBody */
		    {
			ArrayList list = new7();
			push(goTo(3), list, false);
		    }
		    break;
                    case 8: /* reduce AEntitypattern */
		    {
			ArrayList list = new8();
			push(goTo(4), list, false);
		    }
		    break;
                    case 9: /* reduce AIdeEntitytype */
		    {
			ArrayList list = new9();
			push(goTo(5), list, false);
		    }
		    break;
                    case 10: /* reduce AVariableEntitytype */
		    {
			ArrayList list = new10();
			push(goTo(5), list, false);
		    }
		    break;
                    case 11: /* reduce AEntitybody */
		    {
			ArrayList list = new11();
			push(goTo(6), list, false);
		    }
		    break;
                    case 12: /* reduce ASingleAttributes */
		    {
			ArrayList list = new12();
			push(goTo(7), list, false);
		    }
		    break;
                    case 13: /* reduce AListAttributes */
		    {
			ArrayList list = new13();
			push(goTo(7), list, false);
		    }
		    break;
                    case 14: /* reduce AAttribute */
		    {
			ArrayList list = new14();
			push(goTo(8), list, false);
		    }
		    break;
                    case 15: /* reduce AIdeAttributetype */
		    {
			ArrayList list = new15();
			push(goTo(9), list, false);
		    }
		    break;
                    case 16: /* reduce AVariableAttributetype */
		    {
			ArrayList list = new16();
			push(goTo(9), list, false);
		    }
		    break;
                    case 17: /* reduce AStringValue */
		    {
			ArrayList list = new17();
			push(goTo(10), list, false);
		    }
		    break;
                    case 18: /* reduce AEntityValue */
		    {
			ArrayList list = new18();
			push(goTo(10), list, false);
		    }
		    break;
                    case 19: /* reduce AVariableValue */
		    {
			ArrayList list = new19();
			push(goTo(10), list, false);
		    }
		    break;
                    case 20: /* reduce ABodyValue */
		    {
			ArrayList list = new20();
			push(goTo(10), list, false);
		    }
		    break;
                    case 21: /* reduce ASingleConditions */
		    {
			ArrayList list = new21();
			push(goTo(11), list, false);
		    }
		    break;
                    case 22: /* reduce AListConditions */
		    {
			ArrayList list = new22();
			push(goTo(11), list, false);
		    }
		    break;
                    case 23: /* reduce AEqualCondition */
		    {
			ArrayList list = new23();
			push(goTo(12), list, false);
		    }
		    break;
                    }
                    break;
                case ACCEPT:
                    {
                        EOF node2 = (EOF) this.lexer.next();
                        PMain node1 = (PMain) pop().get(0);
                        Start node = new Start(node1, node2);
                        return node;
                    }
                case ERROR:
                    throw new ParserException(this.last_token,
                        "[" + this.last_line + "," + this.last_pos + "] " +
                        Parser.errorMessages[Parser.errors[this.action[1]]]);
            }
        }
    }



    @SuppressWarnings("unchecked")
    ArrayList new0() /* reduce AQueryMain */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PMain pmainNode1;
        {
            // Block
        PQuery pqueryNode2;
        pqueryNode2 = (PQuery)nodeArrayList1.get(0);

        pmainNode1 = new AQueryMain(pqueryNode2);
        }
	nodeList.add(pmainNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new1() /* reduce AAinsertmain1Main */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PMain pmainNode1;
        {
            // Block
        TGreater tgreaterNode2;
        TIdentifier tidentifierNode3;
        @SuppressWarnings("unused") Object nullNode4 = null;
        tgreaterNode2 = (TGreater)nodeArrayList1.get(0);
        tidentifierNode3 = (TIdentifier)nodeArrayList2.get(0);

        pmainNode1 = new AInsertMain(tgreaterNode2, tidentifierNode3, null);
        }
	nodeList.add(pmainNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new2() /* reduce AAinsertmain2Main */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PMain pmainNode1;
        {
            // Block
        TGreater tgreaterNode2;
        TIdentifier tidentifierNode3;
        PEntitybody pentitybodyNode4;
        tgreaterNode2 = (TGreater)nodeArrayList1.get(0);
        tidentifierNode3 = (TIdentifier)nodeArrayList2.get(0);
        pentitybodyNode4 = (PEntitybody)nodeArrayList3.get(0);

        pmainNode1 = new AInsertMain(tgreaterNode2, tidentifierNode3, pentitybodyNode4);
        }
	nodeList.add(pmainNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new3() /* reduce ASimpleQuery */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PQuery pqueryNode1;
        {
            // Block
        PHead pheadNode2;
        TDefinedby tdefinedbyNode3;
        PBody pbodyNode4;
        pheadNode2 = (PHead)nodeArrayList1.get(0);
        tdefinedbyNode3 = (TDefinedby)nodeArrayList2.get(0);
        pbodyNode4 = (PBody)nodeArrayList3.get(0);

        pqueryNode1 = new ASimpleQuery(pheadNode2, tdefinedbyNode3, pbodyNode4);
        }
	nodeList.add(pqueryNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new4() /* reduce AComplexQuery */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList5 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList4 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PQuery pqueryNode1;
        {
            // Block
        PHead pheadNode2;
        TDefinedby tdefinedbyNode3;
        PBody pbodyNode4;
        TQmark tqmarkNode5;
        PConditions pconditionsNode6;
        pheadNode2 = (PHead)nodeArrayList1.get(0);
        tdefinedbyNode3 = (TDefinedby)nodeArrayList2.get(0);
        pbodyNode4 = (PBody)nodeArrayList3.get(0);
        tqmarkNode5 = (TQmark)nodeArrayList4.get(0);
        pconditionsNode6 = (PConditions)nodeArrayList5.get(0);

        pqueryNode1 = new AComplexQuery(pheadNode2, tdefinedbyNode3, pbodyNode4, tqmarkNode5, pconditionsNode6);
        }
	nodeList.add(pqueryNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new5() /* reduce AHead */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PHead pheadNode1;
        {
            // Block
        PEntitytype pentitytypeNode2;
        PEntitybody pentitybodyNode3;
        pentitytypeNode2 = (PEntitytype)nodeArrayList1.get(0);
        pentitybodyNode3 = (PEntitybody)nodeArrayList2.get(0);

        pheadNode1 = new AHead(pentitytypeNode2, pentitybodyNode3);
        }
	nodeList.add(pheadNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new6() /* reduce ASingleBody */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PBody pbodyNode1;
        {
            // Block
        PEntitypattern pentitypatternNode2;
        pentitypatternNode2 = (PEntitypattern)nodeArrayList1.get(0);

        pbodyNode1 = new ASingleBody(pentitypatternNode2);
        }
	nodeList.add(pbodyNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new7() /* reduce AListBody */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PBody pbodyNode1;
        {
            // Block
        PBody pbodyNode2;
        TComa tcomaNode3;
        PEntitypattern pentitypatternNode4;
        pbodyNode2 = (PBody)nodeArrayList1.get(0);
        tcomaNode3 = (TComa)nodeArrayList2.get(0);
        pentitypatternNode4 = (PEntitypattern)nodeArrayList3.get(0);

        pbodyNode1 = new AListBody(pbodyNode2, tcomaNode3, pentitypatternNode4);
        }
	nodeList.add(pbodyNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new8() /* reduce AEntitypattern */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PEntitypattern pentitypatternNode1;
        {
            // Block
        PEntitytype pentitytypeNode2;
        PEntitybody pentitybodyNode3;
        pentitytypeNode2 = (PEntitytype)nodeArrayList1.get(0);
        pentitybodyNode3 = (PEntitybody)nodeArrayList2.get(0);

        pentitypatternNode1 = new AEntitypattern(pentitytypeNode2, pentitybodyNode3);
        }
	nodeList.add(pentitypatternNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new9() /* reduce AIdeEntitytype */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PEntitytype pentitytypeNode1;
        {
            // Block
        TIdentifier tidentifierNode2;
        tidentifierNode2 = (TIdentifier)nodeArrayList1.get(0);

        pentitytypeNode1 = new AIdeEntitytype(tidentifierNode2);
        }
	nodeList.add(pentitytypeNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new10() /* reduce AVariableEntitytype */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PEntitytype pentitytypeNode1;
        {
            // Block
        TVariable tvariableNode2;
        tvariableNode2 = (TVariable)nodeArrayList1.get(0);

        pentitytypeNode1 = new AVariableEntitytype(tvariableNode2);
        }
	nodeList.add(pentitytypeNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new11() /* reduce AEntitybody */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PEntitybody pentitybodyNode1;
        {
            // Block
        TLbracket tlbracketNode2;
        PAttributes pattributesNode3;
        TRbracket trbracketNode4;
        tlbracketNode2 = (TLbracket)nodeArrayList1.get(0);
        pattributesNode3 = (PAttributes)nodeArrayList2.get(0);
        trbracketNode4 = (TRbracket)nodeArrayList3.get(0);

        pentitybodyNode1 = new AEntitybody(tlbracketNode2, pattributesNode3, trbracketNode4);
        }
	nodeList.add(pentitybodyNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new12() /* reduce ASingleAttributes */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PAttributes pattributesNode1;
        {
            // Block
        PAttribute pattributeNode2;
        pattributeNode2 = (PAttribute)nodeArrayList1.get(0);

        pattributesNode1 = new ASingleAttributes(pattributeNode2);
        }
	nodeList.add(pattributesNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new13() /* reduce AListAttributes */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PAttributes pattributesNode1;
        {
            // Block
        PAttributes pattributesNode2;
        TComa tcomaNode3;
        PAttribute pattributeNode4;
        pattributesNode2 = (PAttributes)nodeArrayList1.get(0);
        tcomaNode3 = (TComa)nodeArrayList2.get(0);
        pattributeNode4 = (PAttribute)nodeArrayList3.get(0);

        pattributesNode1 = new AListAttributes(pattributesNode2, tcomaNode3, pattributeNode4);
        }
	nodeList.add(pattributesNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new14() /* reduce AAttribute */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PAttribute pattributeNode1;
        {
            // Block
        PAttributetype pattributetypeNode2;
        TColon tcolonNode3;
        PValue pvalueNode4;
        pattributetypeNode2 = (PAttributetype)nodeArrayList1.get(0);
        tcolonNode3 = (TColon)nodeArrayList2.get(0);
        pvalueNode4 = (PValue)nodeArrayList3.get(0);

        pattributeNode1 = new AAttribute(pattributetypeNode2, tcolonNode3, pvalueNode4);
        }
	nodeList.add(pattributeNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new15() /* reduce AIdeAttributetype */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PAttributetype pattributetypeNode1;
        {
            // Block
        TIdentifier tidentifierNode2;
        tidentifierNode2 = (TIdentifier)nodeArrayList1.get(0);

        pattributetypeNode1 = new AIdeAttributetype(tidentifierNode2);
        }
	nodeList.add(pattributetypeNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new16() /* reduce AVariableAttributetype */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PAttributetype pattributetypeNode1;
        {
            // Block
        TVariable tvariableNode2;
        tvariableNode2 = (TVariable)nodeArrayList1.get(0);

        pattributetypeNode1 = new AVariableAttributetype(tvariableNode2);
        }
	nodeList.add(pattributetypeNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new17() /* reduce AStringValue */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PValue pvalueNode1;
        {
            // Block
        TString tstringNode2;
        tstringNode2 = (TString)nodeArrayList1.get(0);

        pvalueNode1 = new AStringValue(tstringNode2);
        }
	nodeList.add(pvalueNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new18() /* reduce AEntityValue */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PValue pvalueNode1;
        {
            // Block
        TIdentifier tidentifierNode2;
        tidentifierNode2 = (TIdentifier)nodeArrayList1.get(0);

        pvalueNode1 = new AEntityValue(tidentifierNode2);
        }
	nodeList.add(pvalueNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new19() /* reduce AVariableValue */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PValue pvalueNode1;
        {
            // Block
        TVariable tvariableNode2;
        tvariableNode2 = (TVariable)nodeArrayList1.get(0);

        pvalueNode1 = new AVariableValue(tvariableNode2);
        }
	nodeList.add(pvalueNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new20() /* reduce ABodyValue */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PValue pvalueNode1;
        {
            // Block
        PEntitypattern pentitypatternNode2;
        pentitypatternNode2 = (PEntitypattern)nodeArrayList1.get(0);

        pvalueNode1 = new ABodyValue(pentitypatternNode2);
        }
	nodeList.add(pvalueNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new21() /* reduce ASingleConditions */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PConditions pconditionsNode1;
        {
            // Block
        PCondition pconditionNode2;
        pconditionNode2 = (PCondition)nodeArrayList1.get(0);

        pconditionsNode1 = new ASingleConditions(pconditionNode2);
        }
	nodeList.add(pconditionsNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new22() /* reduce AListConditions */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PConditions pconditionsNode1;
        {
            // Block
        PCondition pconditionNode2;
        TComa tcomaNode3;
        PConditions pconditionsNode4;
        pconditionNode2 = (PCondition)nodeArrayList1.get(0);
        tcomaNode3 = (TComa)nodeArrayList2.get(0);
        pconditionsNode4 = (PConditions)nodeArrayList3.get(0);

        pconditionsNode1 = new AListConditions(pconditionNode2, tcomaNode3, pconditionsNode4);
        }
	nodeList.add(pconditionsNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new23() /* reduce AEqualCondition */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PCondition pconditionNode1;
        {
            // Block
        TVariable tvariableNode2;
        TEqual tequalNode3;
        PValue pvalueNode4;
        tvariableNode2 = (TVariable)nodeArrayList1.get(0);
        tequalNode3 = (TEqual)nodeArrayList2.get(0);
        pvalueNode4 = (PValue)nodeArrayList3.get(0);

        pconditionNode1 = new AEqualCondition(tvariableNode2, tequalNode3, pvalueNode4);
        }
	nodeList.add(pconditionNode1);
        return nodeList;
    }



    private static int[][][] actionTable;
/*      {
			{{-1, ERROR, 0}, {5, SHIFT, 1}, {6, SHIFT, 2}, {11, SHIFT, 3}, },
			{{-1, REDUCE, 9}, },
			{{-1, REDUCE, 10}, },
			{{-1, ERROR, 3}, {5, SHIFT, 8}, },
			{{-1, ERROR, 4}, {13, ACCEPT, -1}, },
			{{-1, REDUCE, 0}, },
			{{-1, ERROR, 6}, {2, SHIFT, 9}, },
			{{-1, ERROR, 7}, {3, SHIFT, 10}, },
			{{-1, REDUCE, 1}, {3, SHIFT, 10}, },
			{{-1, ERROR, 9}, {5, SHIFT, 1}, {6, SHIFT, 2}, },
			{{-1, ERROR, 10}, {5, SHIFT, 16}, {6, SHIFT, 17}, },
			{{-1, REDUCE, 5}, },
			{{-1, REDUCE, 2}, },
			{{-1, REDUCE, 3}, {0, SHIFT, 21}, {8, SHIFT, 22}, },
			{{-1, REDUCE, 6}, },
			{{-1, ERROR, 15}, {3, SHIFT, 10}, },
			{{-1, REDUCE, 15}, },
			{{-1, REDUCE, 16}, },
			{{-1, ERROR, 18}, {0, SHIFT, 24}, {4, SHIFT, 25}, },
			{{-1, REDUCE, 12}, },
			{{-1, ERROR, 20}, {1, SHIFT, 26}, },
			{{-1, ERROR, 21}, {5, SHIFT, 1}, {6, SHIFT, 2}, },
			{{-1, ERROR, 22}, {6, SHIFT, 28}, },
			{{-1, REDUCE, 8}, },
			{{-1, ERROR, 24}, {5, SHIFT, 16}, {6, SHIFT, 17}, },
			{{-1, REDUCE, 11}, },
			{{-1, ERROR, 26}, {5, SHIFT, 32}, {6, SHIFT, 33}, {7, SHIFT, 34}, },
			{{-1, REDUCE, 7}, },
			{{-1, ERROR, 28}, {9, SHIFT, 37}, },
			{{-1, REDUCE, 4}, },
			{{-1, REDUCE, 21}, {0, SHIFT, 38}, },
			{{-1, REDUCE, 13}, },
			{{-1, REDUCE, 18}, {3, REDUCE, 9}, },
			{{-1, REDUCE, 19}, {3, REDUCE, 10}, },
			{{-1, REDUCE, 17}, },
			{{-1, REDUCE, 20}, },
			{{-1, REDUCE, 14}, },
			{{-1, ERROR, 37}, {5, SHIFT, 32}, {6, SHIFT, 33}, {7, SHIFT, 34}, },
			{{-1, ERROR, 38}, {6, SHIFT, 28}, },
			{{-1, REDUCE, 23}, },
			{{-1, REDUCE, 22}, },
        };*/
    private static int[][][] gotoTable;
/*      {
			{{-1, 4}, },
			{{-1, 5}, },
			{{-1, 6}, },
			{{-1, 13}, },
			{{-1, 35}, {9, 14}, {21, 27}, },
			{{-1, 15}, {0, 7}, },
			{{-1, 11}, {8, 12}, {15, 23}, },
			{{-1, 18}, },
			{{-1, 19}, {24, 31}, },
			{{-1, 20}, },
			{{-1, 36}, {37, 39}, },
			{{-1, 29}, {38, 40}, },
			{{-1, 30}, },
        };*/
    private static String[] errorMessages;
/*      {
			"expecting: identifier, variable, '>'",
			"expecting: '('",
			"expecting: identifier",
			"expecting: EOF",
			"expecting: ':-'",
			"expecting: '(', EOF",
			"expecting: identifier, variable",
			"expecting: ',', '?', EOF",
			"expecting: ':'",
			"expecting: ',', ')'",
			"expecting: variable",
			"expecting: ',', ')', '?', EOF",
			"expecting: ',', ':-', ')', '?', EOF",
			"expecting: identifier, variable, string",
			"expecting: '='",
			"expecting: ',', EOF",
			"expecting: ',', '(', ')', EOF",
			"expecting: ',', ')', EOF",
        };*/
    private static int[] errors;
/*      {
			0, 1, 1, 2, 3, 3, 4, 1, 5, 6, 6, 4, 3, 7, 7, 1, 8, 8, 9, 9, 8, 6, 10, 11, 6, 12, 13, 7, 14, 3, 15, 9, 16, 16, 17, 17, 9, 13, 10, 15, 3, 
        };*/

    static 
    {
        try
        {
            DataInputStream s = new DataInputStream(
                new BufferedInputStream(
                Parser.class.getResourceAsStream("parser.dat")));

            // read actionTable
            int length = s.readInt();
            Parser.actionTable = new int[length][][];
            for(int i = 0; i < Parser.actionTable.length; i++)
            {
                length = s.readInt();
                Parser.actionTable[i] = new int[length][3];
                for(int j = 0; j < Parser.actionTable[i].length; j++)
                {
                for(int k = 0; k < 3; k++)
                {
                    Parser.actionTable[i][j][k] = s.readInt();
                }
                }
            }

            // read gotoTable
            length = s.readInt();
            gotoTable = new int[length][][];
            for(int i = 0; i < gotoTable.length; i++)
            {
                length = s.readInt();
                gotoTable[i] = new int[length][2];
                for(int j = 0; j < gotoTable[i].length; j++)
                {
                for(int k = 0; k < 2; k++)
                {
                    gotoTable[i][j][k] = s.readInt();
                }
                }
            }

            // read errorMessages
            length = s.readInt();
            errorMessages = new String[length];
            for(int i = 0; i < errorMessages.length; i++)
            {
                length = s.readInt();
                StringBuffer buffer = new StringBuffer();

                for(int j = 0; j < length; j++)
                {
                buffer.append(s.readChar());
                }
                errorMessages[i] = buffer.toString();
            }

            // read errors
            length = s.readInt();
            errors = new int[length];
            for(int i = 0; i < errors.length; i++)
            {
                errors[i] = s.readInt();
            }

            s.close();
        }
        catch(Exception e)
        {
            throw new RuntimeException("The file \"parser.dat\" is either missing or corrupted.");
        }
    }
}
