package com.example.be_study.choi.utils

import spock.lang.Specification

class IdentityValidateUtilsTests extends Specification {

    def "주민등록번호가 내국인이여합니다_단건"() {
        given:
        String identity = "9302251111111"

        when:
        def result =  IdentityValidateUtils.isIdentityNationalUser(identity);

        then:
        result
    }

    def "주민등록번호가 내국인이여합니다_다건"() {
        expect:
        IdentityValidateUtils.isIdentityNationalUser(identity)

        where:
        identity << [
                '9302251111111',
                '9302252111111',
                '9302253111111',
                '9302254111111'
        ]
    }

    def "주민등록번호가 외국인이여합니다_다건"() {
        expect:
        IdentityValidateUtils.isIdentityForeignerUser(identity)

        where:
        identity << [
                '9302255111111',
                '9302256111111',
                '9302257111111',
                '9302258111111'
        ]
    }
}
