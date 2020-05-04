package io.micronaut.starter.generator.asciidoctor

import io.micronaut.starter.generator.CommandSpec
import io.micronaut.starter.options.BuildTool
import io.micronaut.starter.options.Language
import spock.lang.Unroll

class CreateAsciidoctorGroovyGradleSpec extends CommandSpec {

    @Unroll
    void 'test create-app for asciidoctor feature with #language and #buildTool'(Language language, BuildTool buildTool) {
        given:
        generateProject(language, buildTool, ['asciidoctor'])

        when:
        if (buildTool == BuildTool.GRADLE) {
            executeGradleCommand('asciidoctor')
        } else {
            executeMavenCommand("generate-resources")
        }

        then:
        testOutputContains('BUILD SUCCESS')

        where:
        language        | buildTool
        Language.GROOVY         | BuildTool.GRADLE
    }

    @Override
    String getTempDirectoryPrefix() {
        return "test-asciidoctor-createasciidoctorgroovygradlespec"
    }
}