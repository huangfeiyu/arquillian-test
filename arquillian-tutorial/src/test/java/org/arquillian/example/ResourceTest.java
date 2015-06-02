package org.arquillian.example;

import java.io.File;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ResourceTest {
	File[] lib = Maven.resolver()
            .resolve("org.jboss.weld.servlet:weld-servlet:1.1.9.Final")
            .withTransitivity().as(File.class);

	@Resource(name = "jdbc/test")
	DataSource ds;

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addAsManifestResource("arquillian.xml")
				.addAsResource("context.xml", "META-INF/context.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.setWebXML("web.xml");
	}

	@Test
	public void testResource() {
		System.out.println(ds);
		System.out.println("xxxxxx");
	}
}
