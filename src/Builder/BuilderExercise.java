package Builder;

import java.util.ArrayList;
import java.util.Collections;

class CodeElement {
    public String name, type;
    public ArrayList<CodeElement> elements = new ArrayList<>();
    private final int indentSize = 2;
    private final String newLine = System.lineSeparator();

    private final String openBraces = "{";
    private final String closeBraces = "}";

    private final String semicolon = ";";

    public CodeElement() {
    }

    public CodeElement(String name, String type) {
        this.name = name;
        this.type = type;
    }

    private String toStringImpl(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("public class %s%s%s%s", name, newLine, openBraces, newLine));

        for (CodeElement e : elements) {
            String i = String.join("", Collections.nCopies((indent + 1) * indentSize, " "));
            sb.append(String.format("%spublic %s %s%s%s", i, e.type, e.name, semicolon, newLine)); //sb.append(e.toStringImpl(indent + 1));
        }

        sb.append(String.format("%s%s", closeBraces, newLine));
        return sb.toString();
    }

    @Override
    public String toString() {
        return toStringImpl(0);
    }
}

class CodeBuilder {
    private String className;
    private CodeElement clas = new CodeElement();

    public CodeBuilder(String className) {
        this.className = className;
        clas.name = className;
    }

    public CodeBuilder addField(String childName, String childType) {
        CodeElement e = new CodeElement(childName, childType);
        clas.elements.add(e);
        return this;
    }

    @Override
    public String toString() {
        return clas.toString();
    }
}

/*

Suggested solution

package com.activemesa.creational.builder.exercise;

import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

class Field
{
  public String type, name;

  public Field(String name, String type) {
    this.type = type;
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format("public %s %s;", type, name);
  }
}

class Class
{
  public String name;
  public List<Field> fields = new ArrayList<>();

  public Class(){}

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    String nl = System.lineSeparator();
    sb.append("public class " + name).append(nl)
      .append("{").append(nl);
    for (Field f : fields)
      sb.append("  " +  f).append(nl);
    sb.append("}").append(nl);
    return sb.toString();
  }
}

class Builder.CodeBuilder
{
  private Class theClass = new Class();

  public Builder.CodeBuilder(String rootName)
  {
    theClass.name = rootName;
  }

  public Builder.CodeBuilder addField(String name, String type)
  {
    theClass.fields.add(new Field(name, type));
    return this;
  }

  @Override
  public String toString() {
    return theClass.toString();
  }
}

//import org.junit.Test;
//import org.junit.Assert;
//import com.udemy.ucp.*;

 */

public class BuilderExercise {
    public static void main(String[] args) {
        CodeBuilder cb = new CodeBuilder("Person").addField("name", "String").addField("age", "int");
        System.out.println(cb);
    }
}
