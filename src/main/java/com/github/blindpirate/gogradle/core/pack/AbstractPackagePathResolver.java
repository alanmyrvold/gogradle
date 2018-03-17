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

package com.github.blindpirate.gogradle.core.pack;

import com.github.blindpirate.gogradle.core.GolangPackage;
import com.github.blindpirate.gogradle.core.IncompleteGolangPackage;
import com.github.blindpirate.gogradle.util.logging.DebugLog;

import java.util.Optional;

public abstract class AbstractPackagePathResolver implements PackagePathResolver {
    @Override
    @DebugLog
    public Optional<GolangPackage> produce(String packagePath) {
        if (cannotRecognize(packagePath)) {
            return Optional.empty();
        } else if (isIncomplete(packagePath)) {
            return Optional.of(IncompleteGolangPackage.of(packagePath));
        } else {
            return Optional.of(doProduce(packagePath));
        }
    }

    protected abstract GolangPackage doProduce(String packagePath);

    protected abstract boolean isIncomplete(String packagePath);

    protected abstract boolean cannotRecognize(String packagePath);
}
