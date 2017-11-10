@file:Suppress("NOTHING_TO_INLINE")

package io.mockk

import io.mockk.impl.MockKGatewayImpl.Companion.useImpl
import kotlin.reflect.KClass

/**
 * Builds a new mock for specified class
 */
inline fun <reified T : Any> mockk(name: String? = null, vararg moreInterfaces: KClass<*>): T = useImpl {
    MockKDsl.internalMockk(name, *moreInterfaces)
}

/**
 * Builds a new spy for specified class. Copies fields from object if provided
 */
inline fun <reified T : Any> spyk(objToCopy: T? = null, name: String? = null, vararg moreInterfaces: KClass<*>): T = useImpl {
    MockKDsl.internalSpyk(objToCopy, name, *moreInterfaces)
}

/**
 * Creates new capturing slot
 */
inline fun <reified T : Any> slot() = useImpl {
    MockKDsl.internalSlot<T>()
}

/**
 * Creates new lambda args
 */
fun args(vararg v: Any?) = useImpl {
    MockKDsl.internalArgs(*v)
}

/**
 * Starts a block of stubbing. Part of DSL.
 */
inline fun <T> every(noinline stubBlock: MockKMatcherScope.() -> T): MockKStubScope<T> = useImpl {
    MockKDsl.internalEvery(stubBlock)
}

/**
 * Starts a block of stubbing for coroutines. Part of DSL.
 */
inline fun <T> coEvery(noinline stubBlock: suspend MockKMatcherScope.() -> T): MockKStubScope<T> = useImpl {
    MockKDsl.internalCoEvery(stubBlock)
}

/**
 * Verifies calls happened in the past. Part of DSL
 */
inline fun <T> verify(ordering: Ordering = Ordering.UNORDERED,
                      inverse: Boolean = false,
                      atLeast: Int = 1,
                      atMost: Int = Int.MAX_VALUE,
                      exactly: Int = -1,
                      noinline verifyBlock: MockKVerificationScope.() -> T) = useImpl {
    MockKDsl.internalVerify(ordering, inverse, atLeast, atMost, exactly, verifyBlock)
}

/**
 * Verify for coroutines
 */
inline fun <T> coVerify(ordering: Ordering = Ordering.UNORDERED,
                        inverse: Boolean = false,
                        atLeast: Int = 1,
                        atMost: Int = Int.MAX_VALUE,
                        exactly: Int = -1,
                        noinline verifyBlock: suspend MockKVerificationScope.() -> T) = useImpl {
    MockKDsl.internalCoVerify(
            ordering,
            inverse,
            atLeast,
            atMost,
            exactly,
            verifyBlock)
}

/**
 * Shortcut for ordered calls verification
 */
inline fun <T> verifyOrder(inverse: Boolean = false,
                           noinline verifyBlock: MockKVerificationScope.() -> T) = useImpl {
    MockKDsl.internalVerifyOrder(inverse, verifyBlock)
}

/**
 * Shortcut for sequence calls verification
 */
inline fun <T> verifySequence(inverse: Boolean = false,
                              noinline verifyBlock: MockKVerificationScope.() -> T) = useImpl {
    MockKDsl.internalVerifySequence(inverse, verifyBlock)
}

/**
 * Resets information associated with mock
 */
fun clearMocks(vararg mocks: Any, answers: Boolean = true, recordedCalls: Boolean = true, childMocks: Boolean = true) = useImpl {
    MockKDsl.internalClearMocks(mocks = *mocks,
            answers = answers,
            recordedCalls = recordedCalls,
            childMocks = childMocks)
}


/**
 * Executes block of code with registering and unregistering instance factory.
 */
inline fun <reified T: Any> withInstanceFactory(noinline instanceFactory: () -> T, block: () -> Unit) {
    MockKDsl.internalWithInstanceFactory(instanceFactory, block)
}