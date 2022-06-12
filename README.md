# JEXCEL

This project prevents the repetitive use of similar excel export code blocks in different model classes. In this way, developers will get rid of unnecessary and repetitive code blocks and have a cleaner code.

> Note: `The Employee and Student models added for testing reason. You could delete it as you want`

Using
- Import **@Column** annotation to your model class 
- Then annotate the fields that you want to be on exported file
- Set convenient column name and int based column number of the annotation
  - **Be careful while setting column number otherwise given same indexes could overwrite each others**
- Inside the your code call <code>write(Collection< T > collection)</code> method of the **ExportWriter** class
- As a function parameter you can pass any collection like List, Set, Queue etc. 
- Finally exported excel file will be locate under the your project path. 

## Examples of different collection types

### List

<p align="center"><img src="/screenshoots/list1.png" width="600" height="400" /></p>
<p align="center"><img src="/screenshoots/list2.png" width="600" height="400" /></p>


### Set

<p align="center"><img src="/screenshoots/set.png" width="600" height="400" /></p>

### Queue

<p align="center"><img src="/screenshoots/queue.png" width="600" height="400" /></p>
