xjc-javadoc-plugin
==================

XJC javadoc plugin that adds javadocs based on xsd:documentation element.

Versions
--------
0.0.6 @see getter tag is now added to setter methods so that they reference original documentation

0.0.5 Support for attribute documentation

0.0.4 xsd:documentation does not overwrite JAXB custom bindings

0.0.3 Property documentation is added to getter javadocs

0.0.2 Plugin adds javadocs to generated enum types

0.0.1 Initial version - plugin adds javadocs to fields of properties of complex types.

Plugin usage
============

The plugin is not in any public repository yet. Therefore in order to use it one has to retrieve 
the sources and build the project locally.

XJC
---

xjc -classpath /path/to/xjc-javadoc-*.jar -Xjavadoc /path/to/xsd

CXF cxf-codegen-plugin
-------------

There are two things needed:
* Dependency to xjc-javadoc-plugin has to be added to cxf-codegen-plugin
* Extra xjc argument -xjc-Xjavadoc has to be added

Sample configuration:

	<plugin>
		<groupId>org.apache.cxf</groupId>
		<artifactId>cxf-codegen-plugin</artifactId>
		<version>${project.version}</version>
		<executions>
			<execution>
				<id>generate-sources</id>
				<phase>generate-sources</phase>
				<configuration>
					<wsdlOptions>
						<wsdlOption>
							<wsdl>src/main/resources/CustomerService.wsdl</wsdl>
							<extraargs>
								<extraarg>-xjc-Xjavadoc</extraarg>
							</extraargs>
						</wsdlOption>
					</wsdlOptions>
				</configuration>
				<goals>
					<goal>wsdl2java</goal>
				</goals>
			</execution>
		</executions>
		<dependencies>
			<dependency>
				<groupId>org.dpytel.jaxb</groupId>
				<artifactId>xjc-javadoc</artifactId>
				<version>${xjc-javadoc-version}</version>
			</dependency>
		</dependencies>
	</plugin>
