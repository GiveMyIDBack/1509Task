package DemoCS4;

import java.io.Serializable; 

//消息对象 

public class SocketMessage implements Serializable { 

 private static final long serialVersionUID = 1L; 

 private String content; 

 private long messageId; 

 public String getContent() { 

     return content; 
 } 

 public void setContent(String content) { 

     this.content = content; 
 } 

 public long getMessageId() { 

     return messageId; 
 } 

 public void setMessageId(long messageId) { 

     this.messageId = messageId; 
 } 
 
 @Override 
 public String toString() { 

     return getClass().getSimpleName() + "[id=" + getMessageId() + ",content=" + getContent() + "]"; 
 } 

}
