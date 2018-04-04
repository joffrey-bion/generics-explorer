package org.hildan.generics;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;

import org.jetbrains.annotations.NotNull;

/**
 * Handles all kinds of parts of a generic type declaration to produce a value. The meaning of the resulting value is
 * defined by the implementation of this interface. In fact, this interface is a way to provide user-specific
 * domain-related code, while the {@link GenericDeclarationExplorer} encapsulates all the generic processing code.
 *
 * @param <T>
 *         the type of value produced when processing types
 */
public interface GenericTypeHandler<T> {

    /**
     * Handles the {@code void} type.
     *
     * @return the value associated to {@code void}
     */
    T handleVoid();

    /**
     * Handles the given simple class, which is neither an array nor an enum.
     *
     * @param clazz
     *         the non-array, non-enum class to handle
     *
     * @return the value associated to the given class
     */
    T handleSimpleClass(@NotNull Class<?> clazz);

    /**
     * Handles the given enum class. Calling {@code clazz.isEnum()} on the given class is guaranteed to return true.
     *
     * @param clazz
     *         the enum class to handle
     *
     * @return the value associated to the given enum class
     */
    T handleEnumClass(@NotNull Class<?> clazz);

    /**
     * Handles the given array class. Calling {@code clazz.isArray()} on the given class is guaranteed to return true.
     *
     * @param arrayClass
     *         the array class to handle
     * @param handledComponentClass
     *         the value associated to the type of the array elements (already handled)
     *
     * @return the value associated to the given array class
     */
    T handleArrayClass(@NotNull Class<?> arrayClass, T handledComponentClass);

    /**
     * Handles the given {@link GenericArrayType}. It is similar to {@link #handleArrayClass(Class, Object)}, but is
     * called on array types whose component types are either parameterized types or a type variables.
     *
     * @param type
     *         the array type to handle
     * @param handledComponentClass
     *         the value associated to the type of the array elements (already handled)
     *
     * @return the value associated to the given array type
     */
    T handleGenericArray(@NotNull GenericArrayType type, T handledComponentClass);

    /**
     * Handles the given {@link ParameterizedType}.
     *
     * @param type
     *         the whole {@link ParameterizedType} to handle
     * @param handledRawType
     *         the value associated to the raw type of the given parameterized type
     * @param handledTypeParameters
     *         the values associated to the type parameters of the given parameterized type, in the same order as the
     *         type parameters declaration
     *
     * @return the value associated to the given type
     */
    T handleParameterizedType(@NotNull ParameterizedType type, T handledRawType,
            @NotNull List<T> handledTypeParameters);

    /**
     * Handles the given {@link TypeVariable}.
     *
     * @param type
     *         the {@link TypeVariable} to handle
     * @param handledBounds
     *         the values associated to the type of the bounds of the given type variable, in the order they are
     *         declared
     *
     * @return the value associated to the given type variable
     */
    T handleTypeVariable(@NotNull TypeVariable type, @NotNull List<T> handledBounds);

    /**
     * Handles the given {@link WildcardType}.
     *
     * @param type
     *         the {@link WildcardType} to handle
     * @param handledUpperBounds
     *         the values associated to the type of the upper bounds of the given type variable, in the order they are
     *         declared
     * @param handledLowerBounds
     *         the values associated to the type of the lower bounds of the given type variable, in the order they are
     *         declared
     *
     * @return the value associated to the given wildcard type
     */
    T handleWildcardType(@NotNull WildcardType type, @NotNull List<T> handledUpperBounds,
            @NotNull List<T> handledLowerBounds);
}
