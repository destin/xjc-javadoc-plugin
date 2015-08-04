xjc-javadoc-plugin
==================

XJC javadoc plugin that adds javadocs based on xsd:documentation element.

## Plugin has been incorporated into Apache CXF XJC Utils. This version will no longer be maintained.

The new plugin jar can be found in maven repository as org.apache.cxf.xjcplugins:cxf-xjc-javadoc
http://search.maven.org/#search|ga|1|a%3A%22cxf-xjc-javadoc%22

Sample configuration:

    <plugin>
        <groupId>org.jvnet.jaxb2.maven2</groupId>
        <artifactId>maven-jaxb22-plugin</artifactId>
        <version>0.12.3</version>
        <executions>
            <execution>
                <id>xjc</id>
                <goals>
                    <goal>generate</goal>
                </goals>
                <configuration>
                    <args>
                        <arg>-Xjavadoc</arg>
                    </args>
                </configuration>
            </execution>
        </executions>

        <dependencies>
        <dependency>
            <groupId>org.apache.cxf.xjcplugins</groupId>
            <artifactId>cxf-xjc-javadoc</artifactId>
            <version>3.0.4</version>
        </dependency>
        </dependencies>
    </plugin>

If you would like to contribute to the plugin please contact Apache CXF team. Some instructions can be found on:
http://cxf.apache.org/getting-involved.html

Obsolete documentation for original plugin
==========================================

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

Plugin has to be added to the classpath so that XJC can automatically detect it.


XJC maven plugin
----------------

	<plugin>
		<groupId>org.codehaus.mojo</groupId>
		<artifactId>jaxb2-maven-plugin</artifactId>
		<version>1.5</version>
		<executions>
			<execution>
				<id>xjc</id>
				<goals>
					<goal>xjc</goal>
				</goals>
			</execution>
		</executions>
		<configuration>
			<arguments>-Xjavadoc</arguments>
		</configuration>
		<dependencies>
			<dependency>
				<groupId>org.dpytel.jaxb</groupId>
				<artifactId>xjc-javadoc</artifactId>
				<version>${xjc-javadoc-version}</version>
			</dependency>
		</dependencies>
	</plugin>


JAX-WS maven plugin
-------------------

    <plugin>
      <groupId>org.jvnet.jax-ws-commons</groupId>
      <artifactId>jaxws-maven-plugin</artifactId>
      <version>2.3</version>
      <dependencies>
        <!-- put xjc-javadoc-plugin on the jaxws-maven-plugin's classpath -->
        <dependency>
          <groupId>org.dpytel.jaxb</groupId>
		  <artifactId>xjc-javadoc</artifactId>
		  <version>${xjc-javadoc-version}</version>
        </dependency>
      </dependencies>
      <configuration>
        <!-- tell JAXB to actually use xjc-plugins -->
        <xjcArgs>
          <xjcArg>-Xjavadoc</xjcArg>
        </xjcArgs>
      </configuration>
      <executions>
        <execution>
          <goals>
            <goal>wsimport</goal>
          </goals>
        </execution>
        <configuration>
          <wsdlUrls>
            <wsdlUrl>http://example.com/info?WSDL</wsdlUrl>
          </wsdlUrls>
        </configuration>
      </executions>
    </plugin>


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
