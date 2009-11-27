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
                    case 0: /* reduce ADefinitionQuery */
		    {
			ArrayList list = new0();
			push(goTo(0), list, false);
		    }
		    break;
                    case 1: /* reduce AEntity */
		    {
			ArrayList list = new1();
			push(goTo(1), list, false);
		    }
		    break;
                    case 2: /* reduce ASingleAttributes */
		    {
			ArrayList list = new2();
			push(goTo(2), list, false);
		    }
		    break;
                    case 3: /* reduce AListAttributes */
		    {
			ArrayList list = new3();
			push(goTo(2), list, false);
		    }
		    break;
                    case 4: /* reduce AIdeEntityid */
		    {
			ArrayList list = new4();
			push(goTo(3), list, false);
		    }
		    break;
                    case 5: /* reduce AVarEntityid */
		    {
			ArrayList list = new5();
			push(goTo(3), list, false);
		    }
		    break;
                    case 6: /* reduce AAtomicValuetype */
		    {
			ArrayList list = new6();
			push(goTo(4), list, false);
		    }
		    break;
                    case 7: /* reduce AEntityValuetype */
		    {
			ArrayList list = new7();
			push(goTo(4), list, false);
		    }
		    break;
                    case 8: /* reduce AVarValuetype */
		    {
			ArrayList list = new8();
			push(goTo(4), list, false);
		    }
		    break;
                    case 9: /* reduce AEntitydefValuetype */
		    {
			ArrayList list = new9();
			push(goTo(4), list, false);
		    }
		    break;
                    case 10: /* reduce APatternBody */
		    {
			ArrayList list = new10();
			push(goTo(5), list, false);
		    }
		    break;
                    case 11: /* reduce ASinglePattern */
		    {
			ArrayList list = new11();
			push(goTo(6), list, false);
		    }
		    break;
                    case 12: /* reduce AListPattern */
		    {
			ArrayList list = new12();
			push(goTo(6), list, false);
		    }
		    break;
                    case 13: /* reduce AConditionsWithblock */
		    {
			ArrayList list = new13();
			push(goTo(7), list, false);
		    }
		    break;
                    case 14: /* reduce AEmptyWithblock */
		    {
			ArrayList list = new14();
			push(goTo(7), list, false);
		    }
		    break;
                    case 15: /* reduce ASingleConditions */
		    {
			ArrayList list = new15();
			push(goTo(8), list, false);
		    }
		    break;
                    case 16: /* reduce AListConditions */
		    {
			ArrayList list = new16();
			push(goTo(8), list, false);
		    }
		    break;
                    }
                    break;
                case ACCEPT:
                    {
                        EOF node2 = (EOF) this.lexer.next();
                        PQuery node1 = (PQuery) pop().get(0);
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
    ArrayList new0() /* reduce ADefinitionQuery */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PQuery pqueryNode1;
        {
            // Block
        PEntity pentityNode2;
        TDefinedby tdefinedbyNode3;
        PBody pbodyNode4;
        pentityNode2 = (PEntity)nodeArrayList1.get(0);
        tdefinedbyNode3 = (TDefinedby)nodeArrayList2.get(0);
        pbodyNode4 = (PBody)nodeArrayList3.get(0);

        pqueryNode1 = new ADefinitionQuery(pentityNode2, tdefinedbyNode3, pbodyNode4);
        }
	nodeList.add(pqueryNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new1() /* reduce AEntity */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList4 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PEntity pentityNode1;
        {
            // Block
        PEntityid pentityidNode2;
        TLbracket tlbracketNode3;
        PAttributes pattributesNode4;
        TRbracket trbracketNode5;
        pentityidNode2 = (PEntityid)nodeArrayList1.get(0);
        tlbracketNode3 = (TLbracket)nodeArrayList2.get(0);
        pattributesNode4 = (PAttributes)nodeArrayList3.get(0);
        trbracketNode5 = (TRbracket)nodeArrayList4.get(0);

        pentityNode1 = new AEntity(pentityidNode2, tlbracketNode3, pattributesNode4, trbracketNode5);
        }
	nodeList.add(pentityNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new2() /* reduce ASingleAttributes */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PAttributes pattributesNode1;
        {
            // Block
        TIdentifier tidentifierNode2;
        TColon tcolonNode3;
        PValuetype pvaluetypeNode4;
        tidentifierNode2 = (TIdentifier)nodeArrayList1.get(0);
        tcolonNode3 = (TColon)nodeArrayList2.get(0);
        pvaluetypeNode4 = (PValuetype)nodeArrayList3.get(0);

        pattributesNode1 = new ASingleAttributes(tidentifierNode2, tcolonNode3, pvaluetypeNode4);
        }
	nodeList.add(pattributesNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new3() /* reduce AListAttributes */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList5 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList4 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PAttributes pattributesNode1;
        {
            // Block
        TIdentifier tidentifierNode2;
        TColon tcolonNode3;
        PValuetype pvaluetypeNode4;
        TComa tcomaNode5;
        PAttributes pattributesNode6;
        tidentifierNode2 = (TIdentifier)nodeArrayList1.get(0);
        tcolonNode3 = (TColon)nodeArrayList2.get(0);
        pvaluetypeNode4 = (PValuetype)nodeArrayList3.get(0);
        tcomaNode5 = (TComa)nodeArrayList4.get(0);
        pattributesNode6 = (PAttributes)nodeArrayList5.get(0);

        pattributesNode1 = new AListAttributes(tidentifierNode2, tcolonNode3, pvaluetypeNode4, tcomaNode5, pattributesNode6);
        }
	nodeList.add(pattributesNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new4() /* reduce AIdeEntityid */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PEntityid pentityidNode1;
        {
            // Block
        TIdentifier tidentifierNode2;
        tidentifierNode2 = (TIdentifier)nodeArrayList1.get(0);

        pentityidNode1 = new AIdeEntityid(tidentifierNode2);
        }
	nodeList.add(pentityidNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new5() /* reduce AVarEntityid */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PEntityid pentityidNode1;
        {
            // Block
        TVariable tvariableNode2;
        tvariableNode2 = (TVariable)nodeArrayList1.get(0);

        pentityidNode1 = new AVarEntityid(tvariableNode2);
        }
	nodeList.add(pentityidNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new6() /* reduce AAtomicValuetype */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PValuetype pvaluetypeNode1;
        {
            // Block
        TString tstringNode2;
        tstringNode2 = (TString)nodeArrayList1.get(0);

        pvaluetypeNode1 = new AAtomicValuetype(tstringNode2);
        }
	nodeList.add(pvaluetypeNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new7() /* reduce AEntityValuetype */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PValuetype pvaluetypeNode1;
        {
            // Block
        TIdentifier tidentifierNode2;
        tidentifierNode2 = (TIdentifier)nodeArrayList1.get(0);

        pvaluetypeNode1 = new AEntityValuetype(tidentifierNode2);
        }
	nodeList.add(pvaluetypeNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new8() /* reduce AVarValuetype */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PValuetype pvaluetypeNode1;
        {
            // Block
        TVariable tvariableNode2;
        tvariableNode2 = (TVariable)nodeArrayList1.get(0);

        pvaluetypeNode1 = new AVarValuetype(tvariableNode2);
        }
	nodeList.add(pvaluetypeNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new9() /* reduce AEntitydefValuetype */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PValuetype pvaluetypeNode1;
        {
            // Block
        PEntity pentityNode2;
        pentityNode2 = (PEntity)nodeArrayList1.get(0);

        pvaluetypeNode1 = new AEntitydefValuetype(pentityNode2);
        }
	nodeList.add(pvaluetypeNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new10() /* reduce APatternBody */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PBody pbodyNode1;
        {
            // Block
        PPattern ppatternNode2;
        PWithblock pwithblockNode3;
        ppatternNode2 = (PPattern)nodeArrayList1.get(0);
        pwithblockNode3 = (PWithblock)nodeArrayList2.get(0);

        pbodyNode1 = new APatternBody(ppatternNode2, pwithblockNode3);
        }
	nodeList.add(pbodyNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new11() /* reduce ASinglePattern */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PPattern ppatternNode1;
        {
            // Block
        PValuetype pvaluetypeNode2;
        pvaluetypeNode2 = (PValuetype)nodeArrayList1.get(0);

        ppatternNode1 = new ASinglePattern(pvaluetypeNode2);
        }
	nodeList.add(ppatternNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new12() /* reduce AListPattern */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PPattern ppatternNode1;
        {
            // Block
        PValuetype pvaluetypeNode2;
        TComa tcomaNode3;
        PPattern ppatternNode4;
        pvaluetypeNode2 = (PValuetype)nodeArrayList1.get(0);
        tcomaNode3 = (TComa)nodeArrayList2.get(0);
        ppatternNode4 = (PPattern)nodeArrayList3.get(0);

        ppatternNode1 = new AListPattern(pvaluetypeNode2, tcomaNode3, ppatternNode4);
        }
	nodeList.add(ppatternNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new13() /* reduce AConditionsWithblock */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PWithblock pwithblockNode1;
        {
            // Block
        TQmark tqmarkNode2;
        PConditions pconditionsNode3;
        tqmarkNode2 = (TQmark)nodeArrayList1.get(0);
        pconditionsNode3 = (PConditions)nodeArrayList2.get(0);

        pwithblockNode1 = new AConditionsWithblock(tqmarkNode2, pconditionsNode3);
        }
	nodeList.add(pwithblockNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new14() /* reduce AEmptyWithblock */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        PWithblock pwithblockNode1;
        {
            // Block

        pwithblockNode1 = new AEmptyWithblock();
        }
	nodeList.add(pwithblockNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new15() /* reduce ASingleConditions */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PConditions pconditionsNode1;
        {
            // Block
        PValuetype pvaluetypeNode2;
        TEqual tequalNode3;
        PValuetype pvaluetypeNode4;
        pvaluetypeNode2 = (PValuetype)nodeArrayList1.get(0);
        tequalNode3 = (TEqual)nodeArrayList2.get(0);
        pvaluetypeNode4 = (PValuetype)nodeArrayList3.get(0);

        pconditionsNode1 = new ASingleConditions(pvaluetypeNode2, tequalNode3, pvaluetypeNode4);
        }
	nodeList.add(pconditionsNode1);
        return nodeList;
    }



    @SuppressWarnings("unchecked")
    ArrayList new16() /* reduce AListConditions */
    {
        @SuppressWarnings("hiding") ArrayList nodeList = new ArrayList();

        @SuppressWarnings("unused") ArrayList nodeArrayList5 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList4 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList3 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList2 = pop();
        @SuppressWarnings("unused") ArrayList nodeArrayList1 = pop();
        PConditions pconditionsNode1;
        {
            // Block
        PConditions pconditionsNode2;
        TComa tcomaNode3;
        PValuetype pvaluetypeNode4;
        TEqual tequalNode5;
        PValuetype pvaluetypeNode6;
        pconditionsNode2 = (PConditions)nodeArrayList1.get(0);
        tcomaNode3 = (TComa)nodeArrayList2.get(0);
        pvaluetypeNode4 = (PValuetype)nodeArrayList3.get(0);
        tequalNode5 = (TEqual)nodeArrayList4.get(0);
        pvaluetypeNode6 = (PValuetype)nodeArrayList5.get(0);

        pconditionsNode1 = new AListConditions(pconditionsNode2, tcomaNode3, pvaluetypeNode4, tequalNode5, pvaluetypeNode6);
        }
	nodeList.add(pconditionsNode1);
        return nodeList;
    }



    private static int[][][] actionTable;
/*      {
			{{-1, ERROR, 0}, {5, SHIFT, 1}, {6, SHIFT, 2}, },
			{{-1, REDUCE, 4}, },
			{{-1, REDUCE, 5}, },
			{{-1, ERROR, 3}, {10, ACCEPT, -1}, },
			{{-1, ERROR, 4}, {2, SHIFT, 6}, },
			{{-1, ERROR, 5}, {3, SHIFT, 7}, },
			{{-1, ERROR, 6}, {5, SHIFT, 8}, {6, SHIFT, 9}, {7, SHIFT, 10}, },
			{{-1, ERROR, 7}, {5, SHIFT, 15}, },
			{{-1, REDUCE, 7}, {3, REDUCE, 4}, },
			{{-1, REDUCE, 8}, {3, REDUCE, 5}, },
			{{-1, REDUCE, 6}, },
			{{-1, REDUCE, 9}, },
			{{-1, REDUCE, 11}, {0, SHIFT, 17}, },
			{{-1, REDUCE, 0}, },
			{{-1, REDUCE, 14}, {8, SHIFT, 18}, },
			{{-1, ERROR, 15}, {1, SHIFT, 20}, },
			{{-1, ERROR, 16}, {4, SHIFT, 21}, },
			{{-1, ERROR, 17}, {5, SHIFT, 8}, {6, SHIFT, 9}, {7, SHIFT, 10}, },
			{{-1, ERROR, 18}, {5, SHIFT, 8}, {6, SHIFT, 9}, {7, SHIFT, 10}, },
			{{-1, REDUCE, 10}, },
			{{-1, ERROR, 20}, {5, SHIFT, 8}, {6, SHIFT, 9}, {7, SHIFT, 10}, },
			{{-1, REDUCE, 1}, },
			{{-1, REDUCE, 12}, },
			{{-1, ERROR, 23}, {9, SHIFT, 26}, },
			{{-1, REDUCE, 13}, {0, SHIFT, 27}, },
			{{-1, REDUCE, 2}, {0, SHIFT, 28}, },
			{{-1, ERROR, 26}, {5, SHIFT, 8}, {6, SHIFT, 9}, {7, SHIFT, 10}, },
			{{-1, ERROR, 27}, {5, SHIFT, 8}, {6, SHIFT, 9}, {7, SHIFT, 10}, },
			{{-1, ERROR, 28}, {5, SHIFT, 15}, },
			{{-1, REDUCE, 15}, },
			{{-1, ERROR, 30}, {9, SHIFT, 32}, },
			{{-1, REDUCE, 3}, },
			{{-1, ERROR, 32}, {5, SHIFT, 8}, {6, SHIFT, 9}, {7, SHIFT, 10}, },
			{{-1, REDUCE, 16}, },
        };*/
    private static int[][][] gotoTable;
/*      {
			{{-1, 3}, },
			{{-1, 11}, {0, 4}, },
			{{-1, 16}, {28, 31}, },
			{{-1, 5}, },
			{{-1, 12}, {18, 23}, {20, 25}, {26, 29}, {27, 30}, {32, 33}, },
			{{-1, 13}, },
			{{-1, 14}, {17, 22}, },
			{{-1, 19}, },
			{{-1, 24}, },
        };*/
    private static String[] errorMessages;
/*      {
			"expecting: identifier, variable",
			"expecting: '('",
			"expecting: EOF",
			"expecting: ':-'",
			"expecting: identifier, variable, string",
			"expecting: identifier",
			"expecting: ',', '(', ')', '?', '=', EOF",
			"expecting: ',', ')', '?', '=', EOF",
			"expecting: ',', '?', EOF",
			"expecting: '?', EOF",
			"expecting: ':'",
			"expecting: ')'",
			"expecting: ',', ':-', ')', '?', '=', EOF",
			"expecting: '='",
			"expecting: ',', EOF",
			"expecting: ',', ')'",
        };*/
    private static int[] errors;
/*      {
			0, 1, 1, 2, 3, 1, 4, 5, 6, 6, 7, 7, 8, 2, 9, 10, 11, 4, 4, 2, 4, 12, 9, 13, 14, 15, 4, 4, 5, 14, 13, 11, 4, 14, 
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