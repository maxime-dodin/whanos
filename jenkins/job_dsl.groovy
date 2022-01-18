folder('Projects') {
    description('Folder for project.')
}

folder('Whanos base images') {
    description('Folder for whanos base images.')
}


freeStyleJob('Whanos base images/whanos-c') {
    wrappers {
        preBuildCleanup()
    }
    steps {
        dsl {
            shell("docker build -t whanos-c -f ../../images/c/Dockerfile.base .")
        }
    }
}

freeStyleJob('Whanos base images/whanos-ts') {
    wrappers {
        preBuildCleanup()
    }
    steps {
        dsl {
            shell("docker build -t whanos-ts -f ../../images/ts/Dockerfile.base .")
        }
    }
}

freeStyleJob('Whanos base images/whanos-java') {
    wrappers {
        preBuildCleanup()
    }
    steps {
        dsl {
            shell("docker build -t whanos-java -f ../../images/java/Dockerfile.base .")
        }
    }
}

freeStyleJob('Whanos base images/whanos-js') {
    wrappers {
        preBuildCleanup()
    }
    steps {
        dsl {
            shell("docker build -t whanos-js -f ../../images/js/Dockerfile.base .")
        }
    }
}

freeStyleJob('Whanos base images/whanos-befunge') {
    wrappers {
        preBuildCleanup()
    }
    steps {
        dsl {
            shell("docker build -t whanos-befunge -f ../../images/befunge/Dockerfile.base .")
        }
    }
}

freeStyleJob('Whanos base images/whanos-python') {
    wrappers {
        preBuildCleanup()
    }
    steps {
        dsl {
            shell("docker build -t whanos-python -f ../../images/python/Dockerfile.base .")
        }
    }
}

freeStyleJob('Whanos base images/BuildAllBaseImages') {
    wrappers {
        preBuildCleanup()
    }
    steps {
        dsl {
            shell("docker build -t whanos-python -f ../../images/python/Dockerfile.base .")
        }
    }
}



freeStyleJob('link-project') {
    parameters {
        stringParam('GITHUB_NAME', null, 'GitHub repository owner/repo_name (e.g.: "EpitechIT31000/chocolatine")')
        stringParam('DISPLAY_NAME', null, 'Display name for the job')
    }
    steps {
        dsl {
            text('''job("Projects/$DISPLAY_NAME") {
                scm {
                    git {
                        remote {
                            github("$GITHUB_NAME", 'https')
                        }
                    }
                }
                wrappers {
                    preBuildCleanup()
                }
                triggers {
                    pollSCM {
                        scmpoll_spec('* * * * *')
                    }
                }
                steps {
                    shell("/app/language.sh \$DISPLAY_NAME")
                }
            }''')
        }
    }
}
