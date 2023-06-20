# Berufsschule_Lernfeld05_08_Projects

<h1>Usage within IntelliJ IDE</h1>
To use this project from inside the IntelliJ IDE please click the Button in the following picture:<br />
![click_button](https://github.com/Crackminer/Berufsschule_Lernfeld05_08_Projects/assets/87083544/582054b4-a39b-485c-b730-6ce9c32dd8c1)
<br />

Now you select edit configurations:<br />
![Screenshot from 2023-06-20 08-58-44](https://github.com/Crackminer/Berufsschule_Lernfeld05_08_Projects/assets/87083544/2da0abb9-1041-4605-88dc-ac0c2ba18d86)
<br />

Then you click on the small '+'-sign.<br />
![click_plus_button](https://github.com/Crackminer/Berufsschule_Lernfeld05_08_Projects/assets/87083544/f42c8a09-ce26-42a9-acad-a31d9057724b)
<br />

You select the 'Application'.<br />
![click_application_button](https://github.com/Crackminer/Berufsschule_Lernfeld05_08_Projects/assets/87083544/305e4aaf-8460-4eae-9b47-66cfec3e1066)
<br />

You add the classpath:<br />
![click_add_classpath](https://github.com/Crackminer/Berufsschule_Lernfeld05_08_Projects/assets/87083544/37b33bb4-a938-4ada-8f92-520a98c02831)
<br />

You add the desired opener (-Console or -GUI):<br />
![add_arguments](https://github.com/Crackminer/Berufsschule_Lernfeld05_08_Projects/assets/87083544/99facc71-6362-426b-8475-75b0b7c01ba3)
<br />

You give it a rememberable name:<br />
![add_name](https://github.com/Crackminer/Berufsschule_Lernfeld05_08_Projects/assets/87083544/4b9b4eab-eba5-4bcd-911b-152d2eacb281)
<br />

You hit apply:<br />
![click_apply_button](https://github.com/Crackminer/Berufsschule_Lernfeld05_08_Projects/assets/87083544/4f6243df-e566-4ef7-974b-63303ff2151f)
<br />

Now you can select the option from the table and hit 'start' to run the program as desired.<br />
![Screenshot from 2023-06-20 09-01-05](https://github.com/Crackminer/Berufsschule_Lernfeld05_08_Projects/assets/87083544/1a3a3007-8a7c-4169-8095-15de22328e58)
<br />
I strongly recommend you to make both the GUI and the Console available for you.<br />
<br />
<br />
<h1>Building the Project with maven</h1>
To build the project please go into the directory of the project.                           <br />
Make sure you have maven installed for this and make sure that the active jdk is jdk 18.    <br />

After you made sure of that please use the following command:

```basic
mvn -U clean install
```

<br />

To start the program please specify the input method like this: <br />
                                                                <br />
Console:

```basic
java -jar target/Lernfeld08Project01-1.0-SNAPSHOT.jar -Console
```

<br />

Gui:

```basic
java -jar target/Lernfeld08Project01-1.0-SNAPSHOT.jar -GUI
```

<br />

Still to do: <br />
- GUI                             
  - Patrick
  - Finja
- DataLayerXml + Subclasses       
  - (Patrick) 
- DataLayerSqlite + Subclasses    
  - (Patrick)
<br />
<br />
