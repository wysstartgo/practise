package autocomplete;

import com.amazonaws.services.devicefarm.model.ArgumentException;
import org.apache.commons.lang.StringUtils;

import java.util.HashSet;

/// <summary>
/// The node type.
/// Indicates the word completed or not.
/// </summary>
public enum NodeType
{
    COMPLETED,
    UNCOMPLETED
};

/// <summary>
/// The tree node.
/// </summary>
class Node
{
    private int ALPHABET_SIZE = 26;

    private char Word;

    private NodeType Type;

    private Node[] Child;

    /// <summary>
    /// Initializes a new instance of the <see cref="Node"/> class.
    /// </summary>
    /// <param name="word">The word.</param>
    /// <param name="nodeType">Type of the node.</param>
    public Node(char word, NodeType nodeType)
    {
        this.Word = word;
        this.Type = nodeType;
        this.Child = new Node[ALPHABET_SIZE];
    }

    public char getWord() {
        return Word;
    }

    public void setWord(char word) {
        Word = word;
    }

    public NodeType getType() {
        return Type;
    }

    public void setType(NodeType type) {
        Type = type;
    }

    public Node[] getChild() {
        return Child;
    }

    public void setChild(Node[] child) {
        Child = child;
    }
}

class Trie
{
    private int ALPHABET_SIZE = 26;

    private Node _root;

    private HashSet<String> _hashSet;

    public Trie()
    {
        _root = CreateNode(' ');
    }

    public Node CreateNode(char word)
    {
        Node node = new Node(word, NodeType.UNCOMPLETED);
        return node;
    }


    /// <summary>
    /// Inserts the specified node.
    /// </summary>
    /// <param name="node">The node.</param>
    /// <param name="word">The word need to insert.</param>
    private void Insert(Node node, String word)
    {
        Node temp = node;
        for (char t:word.toCharArray()) {
            if(null == temp.getChild()[this.CharToIndex(t)]){
                temp.getChild()[this.CharToIndex(t)] = this.CreateNode(t);
            }
            temp = temp.getChild()[this.CharToIndex(t)];
        }

        temp.setType(NodeType.COMPLETED);
    }

    /// <summary>
    /// Inserts the specified word.
    /// </summary>
    /// <param name="word">Retrieval word.</param>
    public void Insert(String word)
    {
        if (StringUtils.isBlank(word))
        {
            throw new ArgumentException("word");
        }

        Insert(_root, word);
    }

    /// <summary>
    /// Finds the specified word.
    /// </summary>
    /// <param name="word">Retrieval word.</param>
    /// <returns>The tree node.</returns>
    public Node Find(String word)
    {
        if (StringUtils.isBlank(word))
        {
            throw new ArgumentException("word");
        }
        char[] chars = word.toCharArray();
        int i = 0;
        Node temp = _root;
        HashSet words = new HashSet<String>();
        while (i < word.length())
        {
            if (null == temp.getChild()[this.CharToIndex(chars[i])])
            {
                return null;
            }

            temp = temp.getChild()[this.CharToIndex(chars[i++])];
        }

        if (temp != null && NodeType.COMPLETED == temp.getType())
        {
            _hashSet = new HashSet<String>();
            _hashSet.add(word);
            return temp;
        }

        return null;
    }

    /// <summary>
    /// Finds the simlar word.
    /// </summary>
    /// <param name="word">The words have same prefix.</param>
    /// <returns>The collection of similar words.</returns>
    public HashSet<String> FindSimilar(String word)
    {
        Node node = Find(word);


        DFS(word, node);
        return _hashSet;
    }

    /// <summary>
    /// DFSs the specified prefix.
    /// </summary>
    /// <param name="prefix">Retrieval prefix.</param>
    /// <param name="node">The node.</param>
    private void DFS(String prefix, Node node)
    {
        for (int i = 0; i < ALPHABET_SIZE; i++)
        {
            if (node.getChild()[i] != null)
            {
                DFS(prefix + node.getChild()[i].getWord(), node.getChild()[i]);
                if (NodeType.COMPLETED == node.getChild()[i].getType())
                {
                    _hashSet.add(prefix + node.getChild()[i].getWord());
                }
            }
        }
    }

    /// <summary>
    /// Converts char to index.
    /// </summary>
    /// <param name="ch">The char need to convert.</param>
    /// <returns>The index.</returns>
    private int CharToIndex(char ch)
    {
        return ch - 'a';
    }
}