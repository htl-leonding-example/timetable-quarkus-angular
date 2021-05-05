#!/bin/zsh
mvn io.quarkus:quarkus-maven-plugin:1.13.3.Final:create -e \
    -DprojectGroupId=at.htl.timetable \
    -DprojectArtifactId=timetable-backend \
    -DclassName="at.htl.timetable.boundary.DemoEndpoint" \
    -Dpath="/api" \
    -Dextensions="resteasy, resteasy-jsonb, hibernate-orm-panache, jdbc-derby, smallrye-openapi"

