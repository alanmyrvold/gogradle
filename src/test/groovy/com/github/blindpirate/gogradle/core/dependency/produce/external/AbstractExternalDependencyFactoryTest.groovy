/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.blindpirate.gogradle.core.dependency.produce.external

import com.github.blindpirate.gogradle.core.dependency.AbstractResolvedDependency
import com.github.blindpirate.gogradle.core.dependency.GogradleRootProject
import com.github.blindpirate.gogradle.core.dependency.NotationDependency
import com.github.blindpirate.gogradle.core.dependency.ResolvedDependency
import com.github.blindpirate.gogradle.core.dependency.parse.MapNotationParser
import com.github.blindpirate.gogradle.core.pack.StandardPackagePathResolver
import com.github.blindpirate.gogradle.support.WithResource
import org.junit.Before
import org.mockito.Mock

import java.nio.file.Path

import static org.mockito.Mockito.*

@WithResource('')
class AbstractExternalDependencyFactoryTest {
    File resource

    @Mock
    MapNotationParser mapNotationParser
    @Mock
    NotationDependency dependency
    @Mock
    AbstractResolvedDependency module
    @Mock
    StandardPackagePathResolver standardPackagePathResolver
    @Mock
    ResolvedDependency parentDependency
    @Mock
    GogradleRootProject gogradleRootProject

    @Before
    void superSetUp() {
        when(mapNotationParser.parse(anyMap())).thenReturn(dependency)
        when(dependency.getName()).thenReturn('name')
        when(standardPackagePathResolver.isStandardPackage(any(Path))).thenReturn(false)
        when(gogradleRootProject.getName()).thenReturn('github.com/my/project')
        when(parentDependency.getName()).thenReturn('github.com/target/project')
    }

    void verifyMapParsed(Map map) {
        verify(mapNotationParser).parse(eq(map))
    }
}
