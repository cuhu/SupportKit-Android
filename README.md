Support Kit by Dreamr
=====================

Simple support kit library that inserts a support dialog into your application. Users can select from a list of support options and send it using a client of their choice.
![Alt text](https://github.com/dreamr-uk/SupportKit-Android/blob/staging/gitsite/support1.png | width=100)
![Alt text](https://github.com/dreamr-uk/SupportKit-Android/blob/staging/gitsite/support2.png)

Default support options are:
* "I have an issue with payments"
* "I have found a bug"
* "There may be a legal issue"
* "I have some feedback"

The support kit is added using:

```java
SupportKitBuilder supportKitBuilder = new SupportKitBuilder();
supportKitBuilder.setEmailTo("Example.email@gmail.com").show(getFragmentManager());
```

Adding custom support options is done by adding an issue:
```java
supportKitBuilder.addIssue("some custom support option", "IssueType");
```
