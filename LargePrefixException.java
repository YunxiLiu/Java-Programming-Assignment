/**
 * a new defined exception to handle the situation where 
 * the size of prefix is larger than the number of words in the sample text
 * @author Yunxi Liu
 * USC loginid: yunxiliu
 * CS 455 PA4
 * Fall 2016
 */

import java.io.IOException;

public class LargePrefixException extends IOException
{
   public LargePrefixException() {}
}
