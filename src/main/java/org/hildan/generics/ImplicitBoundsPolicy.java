package org.hildan.generics;

import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/**
 * When a {@link TypeVariable} or {@link WildcardType} has no declared upper bound, it implicitly has an upper bound of
 * type {@link Object}. An {@code ImplicitBoundsPolicy} defines whether to {@link #IGNORE} or {@link #PROCESS} this
 * bound. Please note that an explicit {@link Object} upper bound cannot be distinguished from an implicit upper bound,
 * and thus is subject to the {@code ImplicitBoundsPolicy} as well.
 */
public enum ImplicitBoundsPolicy {
    /**
     * When using this policy, the {@link GenericDeclarationExplorer} ignores implicit {@code Object} upper bounds. This
     * means that the {@link GenericTypeHandler} is not called on the {@code Object} class when processing an unbounded
     * {@link TypeVariable} or {@link WildcardType}, and the list of upper bounds given to {@link
     * GenericTypeHandler#handleTypeVariable} and {@link GenericTypeHandler#handleWildcardType} is empty in this case.
     */
    IGNORE,
    /**
     * When using this policy, the {@link GenericDeclarationExplorer} takes implicit {@code Object} upper bounds into
     * account. This means that the {@link GenericTypeHandler} is called on the {@code Object} class when processing
     * an unbounded {@link TypeVariable} or {@link WildcardType}, and the list of upper bounds given to {@link
     * GenericTypeHandler#handleTypeVariable} and {@link GenericTypeHandler#handleWildcardType} contains the value
     * that the handler associated with the {@code Object} class in this case.
     */
    PROCESS
}
